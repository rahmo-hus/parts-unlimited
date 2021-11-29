package net.croz.unlimited.parts.services.sales;

import net.croz.unlimited.parts.model.sales.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    int save(Product product);
    Product changePrice(Long id, Double price);
    int saveProduct(Product product);
    int delete(Long id);
}
