package net.croz.unlimited.parts.controller;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.DiscountRequest;
import net.croz.unlimited.parts.dto.DiscountResponse;
import net.croz.unlimited.parts.services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping()
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity<List<DiscountResponse>> getAll() {
        return ResponseEntity.ok(discountService.getAll());
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity<Void> addDiscount(@RequestBody DiscountRequest discount) {
        discountService.save(discount);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        discountService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
