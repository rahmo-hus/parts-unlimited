package net.croz.unlimited.parts.mapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.croz.unlimited.parts.dto.CarResponse;
import net.croz.unlimited.parts.dto.DiscountResponse;
import net.croz.unlimited.parts.dto.PartResponse;
import net.croz.unlimited.parts.model.Discount;
import net.croz.unlimited.parts.model.Part;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PartToPartResponseMapper {

    private final CarToCarResponseMapper carToCarResponseMapper;
    private final ModelMapper modelMapper;

    @SneakyThrows
    public PartResponse convert(Part part) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        PartResponse partResponse = new PartResponse();
        partResponse.setImage(part.getImage());
        partResponse.setName(part.getName());
        partResponse.setType(part.getType());
        partResponse.setQuantity(part.getQuantity());
        partResponse.setId(part.getId());
        partResponse.setManufacturer(part.getManufacturer());
        partResponse.setBrand(part.getBrand());
        partResponse.setCategory(part.getCategory());
        partResponse.setCode(part.getCode());
        partResponse.setDescription(part.getDescription());
        partResponse.setSerial(part.getSerial());
        partResponse.setProductionDate(formatter.format(part.getProductionDate()));
        if (part.getDiscount() != null) {
            Discount discount = part.getDiscount();
            SimpleDateFormat rfc339DateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            if (discount.getStartDate().equals(rfc339DateFormatter.format(new Date())) || discount.getEndDate().equals(rfc339DateFormatter.format(new Date())) ||
                    (rfc339DateFormatter.parse(discount.getStartDate()).before(new Date()) && rfc339DateFormatter.parse(discount.getEndDate()).after(new Date()))) {
                Double oldPrice = part.getPrice();
                partResponse.setDiscount(modelMapper.map(part.getDiscount(), DiscountResponse.class));
                partResponse.setPrice(oldPrice - oldPrice * part.getDiscount().getDiscountPercentage() / 100);
                partResponse.getDiscount().setActive(true);
            } else {
                partResponse.setPrice(part.getPrice());
                partResponse.setDiscount(modelMapper.map(discount, DiscountResponse.class));
                partResponse.getDiscount().setActive(false);
            }
        } else
            partResponse.setPrice(part.getPrice());
        List<CarResponse> carResponses = new ArrayList<>();
        part.getCars().forEach(car -> carResponses.add(carToCarResponseMapper.convert(car)));
        partResponse.setCars(carResponses);

        return partResponse;
    }
}
