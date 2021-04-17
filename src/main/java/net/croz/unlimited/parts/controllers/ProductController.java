package net.croz.unlimited.parts.controllers;

import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.ExceptionResponse;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.models.sales.Product;
import net.croz.unlimited.parts.payload.response.MessageResponse;
import net.croz.unlimited.parts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

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
    @PreAuthorize("hasRole('SALES') or hasRole('CUSTOMER')")
    List<Product> getProducts(){
        List<Product> products = productRepository.getProducts();
        return products;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('SALES')")
    ResponseEntity updatePrice(@PathVariable Long id,@Valid @RequestBody Map<String, Double> price){
         int   success = productRepository.changePrice(id, price.get("price"));
        return ResponseEntity.ok().body(new MessageResponse("Product updated successfully"));
    }

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('SALES')")
    ResponseEntity addProduct(@Valid @RequestBody Product product) {
        int success = productRepository.save(product);
       return success == 1 ? ResponseEntity.ok().body(new MessageResponse("Product saved successfully")) : ResponseEntity.status(404).body(new MessageResponse("Product not found"));

    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('SALES')")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long id){
        Map<String, Boolean> response = new HashMap<>();
        int success = productRepository.delete(id);
        response.put("deleted", success == 1);
        return response;
    }
}
