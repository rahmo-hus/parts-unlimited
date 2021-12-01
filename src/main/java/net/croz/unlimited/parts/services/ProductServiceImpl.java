package net.croz.unlimited.parts.services;


import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.model.sales.Product;
import net.croz.unlimited.parts.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    @Override
    public int save(Product product) {
        int affectedRows;
        try {
            affectedRows = productRepository.save(product);
        }
        catch (Throwable e){
            throw new DuplicateItemException("Product with serial "+product.getSerial()+" already exists.");
        }
        if(affectedRows == 0)
            throw new NoSuchElementFoundException("Cannot add product. There is no part with serial "+product.getSerial());
        return affectedRows;
    }

    @Override
    public Product changePrice(Long id, Double price) {
        return productRepository.changePrice(id, price);
    }

    @Override
    public int saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public int delete(Long id) {
        return productRepository.delete(id);
    }
}
