package net.croz.unlimited.parts.services.sales;

import net.croz.unlimited.parts.model.sales.Discount;

import java.util.List;


public interface DiscountService {
    List<Discount> getAll();
    int saveProductToDiscount(Long serial, Long id);
    int save(Discount discount);
}
