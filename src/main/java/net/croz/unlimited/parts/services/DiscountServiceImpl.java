package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.model.sales.Discount;
import net.croz.unlimited.parts.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService{

    final DiscountRepository discountRepository;

    @Override
    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public int saveProductToDiscount(Long serial, Long id) {
        return discountRepository.saveProductToDiscount(serial, id);
    }

    @Override
    public int save(Discount discount) {
        return discountRepository.save(discount);
    }
}