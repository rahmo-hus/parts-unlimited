package net.croz.unlimited.parts.services;


import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.model.Product;
import net.croz.unlimited.parts.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    public int save(Product product) {
        int affectedRows;
        try {
            affectedRows = productRepository.save(product);
        } catch (Throwable e) {
            throw new DuplicateItemException("Product with serial " + product.getSerial() + " already exists.");
        }
        if (affectedRows == 0)
            throw new NoSuchElementFoundException("Cannot add product. There is no part with serial " + product.getSerial());
        return affectedRows;
    }

    public Product changePrice(Long id, Double price) {
        return productRepository.changePrice(id, price);
    }

    public int saveProduct(Product product) {
        return productRepository.save(product);
    }

    public int delete(Long id) {
        return productRepository.delete(id);
    }
}
