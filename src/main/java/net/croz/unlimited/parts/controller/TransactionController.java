package net.croz.unlimited.parts.controller;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.TransactionResponse;
import net.croz.unlimited.parts.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/get-by-user/{userId}")
    public ResponseEntity<List<TransactionResponse>> findAllByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.findAllByUserId(userId));
    }
}
