package net.croz.unlimited.parts.controllers;


import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.ExceptionResponse;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.models.sales.Discount;
import net.croz.unlimited.parts.models.sales.Product;
import net.croz.unlimited.parts.repository.DiscountRepository;
import net.croz.unlimited.parts.repository.ProductRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(NoSuchElementFoundException exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateItemException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionResponse> handleDuplicateElementException(DuplicateItemException e){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("DUPLICATE_ITEM");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('SALES')")
    List<Product> getProducts(){
        List<Product> products = productRepository.getProducts();
        return products;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('SALES')")
    int updatePrice(@PathVariable Long id,@Valid @RequestBody Map<String, Double> price){
         int   success = productRepository.update(id, price.get("price"));
        return success; //return

    }

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('SALES')")
    ResponseEntity addProduct(@RequestBody Product product) throws SQLException {

        int success = productRepository.save(product);
       return success == 1 ? ResponseEntity.ok().build() : ResponseEntity.status(404).build(); //TODO: do

    }
    @PostMapping("/discount")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity addDiscount(@RequestBody Discount discount){
        var d = discountRepository.save(discount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-to-discount/{dId}")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity addToDiscount(@PathVariable(name = "dId") Long discountId, @RequestBody List<Product> products){
       /* var val =discountRepository.saveProductToDiscount(discountId, products);
        return val!=0 ? ResponseEntity.status(200).build(): ResponseEntity.status(500).build();*/
        return ResponseEntity.ok().build(); //TODO:fix
    }

    @GetMapping("/get-all-discounts")
    @PreAuthorize("hasRole('SALES')")
    public List<Discount> getAll(){
        return discountRepository.findAll();
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('SALES')")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long id){
        Map<String, Boolean> response = new HashMap<>();
        if(productRepository.findById(id) == null) {
            productRepository.delete(id);
            response.put("deleted", Boolean.FALSE);
        }
        else
            response.put("deleted", Boolean.TRUE);
        return response;
    }
}
