package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.TransactionResponse;
import net.croz.unlimited.parts.mapper.TransactionToTransactionResponseMapper;
import net.croz.unlimited.parts.model.Transaction;
import net.croz.unlimited.parts.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionToTransactionResponseMapper transactionToTransactionResponseMapper;

    public List<TransactionResponse> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponse> transactionResponses = new ArrayList<>();

        transactions.forEach(transaction -> transactionResponses.add(transactionToTransactionResponseMapper.convert(transaction)));

        return transactionResponses;
    }

    public List<TransactionResponse> findAllByUserId(Long userId){
        List<Transaction> transactions = transactionRepository.findAllByUserId(userId);
        List<TransactionResponse> transactionResponses = new ArrayList<>();

        transactions.forEach(transaction -> transactionResponses.add(transactionToTransactionResponseMapper.convert(transaction)));

        return transactionResponses;
    }
}
