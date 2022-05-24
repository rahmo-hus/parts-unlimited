package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.DiscountRequest;
import net.croz.unlimited.parts.dto.DiscountResponse;
import net.croz.unlimited.parts.model.Discount;
import net.croz.unlimited.parts.model.Part;
import net.croz.unlimited.parts.repository.DiscountRepository;
import net.croz.unlimited.parts.repository.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final ModelMapper modelMapper;
    private final PartRepository partRepository;
    public List<DiscountResponse> getAll() {
        List<DiscountResponse> discountResponses = new ArrayList<>();
        List<Discount> discounts = discountRepository.findAll();
        discounts.forEach(discount -> discountResponses.add(modelMapper.map(discount, DiscountResponse.class)));

        return discountResponses;
    }

    public int saveProductToDiscount(Long serial, Long id) {
        return discountRepository.saveProductToDiscount(serial, id);
    }

    public void delete(Long id){
        discountRepository.deleteById(id);
    }

    public void save(DiscountRequest discountRequest) {
        Part part = partRepository.getOne(discountRequest.getPartId());

        Discount discount = new Discount();
        discount.setDiscountPercentage(discountRequest.getDiscountPercentage());
        discount.setEndDate(discountRequest.getEndDate());
        discount.setStartDate(discountRequest.getStartDate());
        Discount d = discountRepository.save(discount);
        part.setDiscount(d);
        partRepository.save(part);
    }
}
