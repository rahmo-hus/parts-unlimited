package net.croz.unlimited.parts.repository.sales;

import net.croz.unlimited.parts.model.sales.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    int save(Product product);
    List<Product> getProducts();
    int delete(Long id);
    Product changePrice(Long id, Double price);

}
