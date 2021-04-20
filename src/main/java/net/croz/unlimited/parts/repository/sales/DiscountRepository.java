package net.croz.unlimited.parts.repository.sales;

import net.croz.unlimited.parts.models.sales.Discount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository {
    int save(Discount discount);
    int saveProductToDiscount(Long productSerial, Long discountId);
    List<Discount> findAll();
}
