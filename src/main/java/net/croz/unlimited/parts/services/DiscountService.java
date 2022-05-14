package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.model.Discount;
import net.croz.unlimited.parts.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {

    final DiscountRepository discountRepository;

    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    public int saveProductToDiscount(Long serial, Long id) {
        return discountRepository.saveProductToDiscount(serial, id);
    }

    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }
}
