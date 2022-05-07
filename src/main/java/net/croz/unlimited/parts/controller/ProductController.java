package net.croz.unlimited.parts.controller;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.MessageResponse;
import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.ExceptionResponse;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.model.Product;
import net.croz.unlimited.parts.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(NoSuchElementFoundException exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateItemException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionResponse> handleDuplicateElementException(DuplicateItemException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("DUPLICATE_ITEM");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('SALES') or hasRole('CUSTOMER')")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('SALES')")
    public Product updatePrice(@PathVariable Long id, @Valid @RequestBody Map<String, Double> price) {
        return productService.changePrice(id, price.get("price"));
    }

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        int success = productService.save(product);
        return success == 1 ? ResponseEntity.ok().body(product) : ResponseEntity.status(404).body(new MessageResponse("Product not found"));

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable(value = "id") Long id) {
        int success = productService.delete(id);
        return success == 1 ? ResponseEntity.ok().body(new MessageResponse("Product deleted successfully"))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Delete error:Product " + id + " does not exist"));
    }
}
