package net.croz.unlimited.parts.controller;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.CreditCardDTO;
import net.croz.unlimited.parts.services.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/purchase")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<Boolean> purchase(@RequestBody CreditCardDTO creditCardDTO) {
        return ResponseEntity.ok(creditCardService.purchase(creditCardDTO));
    }
}
