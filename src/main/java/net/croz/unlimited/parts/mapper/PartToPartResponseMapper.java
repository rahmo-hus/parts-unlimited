package net.croz.unlimited.parts.mapper;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.CarResponse;
import net.croz.unlimited.parts.dto.PartResponse;
import net.croz.unlimited.parts.model.Part;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PartToPartResponseMapper {

    private final CarToCarResponseMapper carToCarResponseMapper;

    public PartResponse convert(Part part) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        PartResponse partResponse = new PartResponse();
        partResponse.setImage(part.getImage());
        partResponse.setName(part.getName());
        partResponse.setType(part.getType());
        partResponse.setId(part.getId());
        partResponse.setManufacturer(part.getManufacturer());
        partResponse.setBrand(part.getBrand());
        partResponse.setCategory(part.getCategory());
        partResponse.setCode(part.getCode());
        partResponse.setDescription(part.getDescription());
        partResponse.setSerial(part.getSerial());
        partResponse.setPrice(part.getPrice());
        partResponse.setProductionDate(formatter.format(part.getProductionDate()));

        List<CarResponse> carResponses = new ArrayList<>();
        part.getCars().forEach(car->carResponses.add(carToCarResponseMapper.convert(car)));
        partResponse.setCars(carResponses);

        return partResponse;
    }
}
