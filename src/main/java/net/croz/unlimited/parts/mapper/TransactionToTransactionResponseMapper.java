package net.croz.unlimited.parts.mapper;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.PartResponse;
import net.croz.unlimited.parts.dto.TransactionResponse;
import net.croz.unlimited.parts.dto.UserDTO;
import net.croz.unlimited.parts.model.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionToTransactionResponseMapper {

    private final ModelMapper modelMapper;
    private final PartToPartResponseMapper partToPartResponseMapper;

    public TransactionResponse convert(Transaction transaction){
        List<PartResponse> partResponses = new ArrayList<>();
        transaction.getParts().forEach(part -> partResponses.add(partToPartResponseMapper.convert(part)));

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setInfo(transaction.getInfo());
        transactionResponse.setQuantity(transaction.getQuantity());
        transactionResponse.setUser(modelMapper.map(transaction.getUser(), UserDTO.class));
        transactionResponse.setParts(partResponses);

        return transactionResponse;
    }
}
