package net.croz.unlimited.parts.controllers;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.models.sales.Discount;
import net.croz.unlimited.parts.payload.response.MessageResponse;
import net.croz.unlimited.parts.repository.sales.DiscountRepository;
import net.croz.unlimited.parts.services.sales.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping("/get-all-discounts")
    @PreAuthorize("hasRole('SALES')")
    public List<Discount> getAll(){
        return discountService.getAll();
    }

    @PostMapping("/add-to-discount/{dId}")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity addToDiscount(@PathVariable(name = "dId") Long discountId, @RequestBody Map<String, Long> serial){
        var val =discountService.saveProductToDiscount(serial.get("serial"), discountId);
        return val!=0 ? ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Product added to discount successfully")):
                ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new MessageResponse("Unable to add product to discount. Check discount date or if the product is already added to discount"));
    }
    @PostMapping("/save-discount")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity addDiscount(@RequestBody Discount discount){
        var d = discountService.save(discount);
        return d != 0 ? ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Discount added successfully"))
                :ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Unable to add discount"));
    }


}
