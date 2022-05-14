package net.croz.unlimited.parts.controller;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.MessageResponse;
import net.croz.unlimited.parts.model.Discount;
import net.croz.unlimited.parts.services.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping("/get-all-discounts")
    @PreAuthorize("hasRole('SALES')")
    public List<Discount> getAll() {
        return discountService.getAll();
    }

    @PostMapping("/add-to-discount/{dId}")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity addToDiscount(@PathVariable(name = "dId") Long discountId, @RequestBody Map<String, Long> serial) {
        int val = discountService.saveProductToDiscount(serial.get("serial"), discountId);
        return val != 0 ? ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Product added to discount successfully")) :
                ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new MessageResponse("Unable to add product to discount. Check discount date or if the product is already added to discount"));
    }

    @PostMapping("/save-discount")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity addDiscount(@RequestBody Discount discount) {
        return ResponseEntity.ok(discountService.save(discount));
    }


}
