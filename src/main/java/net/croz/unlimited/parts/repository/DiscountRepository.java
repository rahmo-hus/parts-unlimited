package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.model.Discount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository {
    int save(Discount discount);

    int saveProductToDiscount(Long productSerial, Long discountId);

    List<Discount> findAll();
}
