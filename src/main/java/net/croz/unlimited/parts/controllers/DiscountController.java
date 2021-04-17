package net.croz.unlimited.parts.controllers;

import net.croz.unlimited.parts.models.sales.Discount;
import net.croz.unlimited.parts.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    @Autowired
    DiscountRepository discountRepository;

    @GetMapping("/get-all-discounts")
    @PreAuthorize("hasRole('SALES')")
    public List<Discount> getAll(){
        return discountRepository.findAll();
    }

    @PostMapping("/add-to-discount/{dId}")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity addToDiscount(@PathVariable(name = "dId") Long discountId, @RequestBody Map<String, Long> serial){
        var val =discountRepository.saveProductToDiscount(serial.get("serial"), discountId);
        return val!=0 ? ResponseEntity.status(200).build(): ResponseEntity.status(300).build();
    }
    @PostMapping("/save-discount")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity addDiscount(@RequestBody Discount discount){
        var d = discountRepository.save(discount);
        return ResponseEntity.ok().build();
    }


}
