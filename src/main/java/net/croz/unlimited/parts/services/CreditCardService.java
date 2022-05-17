package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.CreditCardDTO;
import net.croz.unlimited.parts.model.CreditCard;
import net.croz.unlimited.parts.model.Part;
import net.croz.unlimited.parts.model.Transaction;
import net.croz.unlimited.parts.model.User;
import net.croz.unlimited.parts.repository.CreditCardRepository;
import net.croz.unlimited.parts.repository.PartRepository;
import net.croz.unlimited.parts.repository.TransactionRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;
    private final PartRepository partRepository;


    public Boolean purchase(CreditCardDTO creditCardDTO, List<Long> iDs) {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CreditCard creditCard = creditCardRepository.getOneByNumberAndFirstNameAndLastNameAndCvv(creditCardDTO.getNumber(), creditCardDTO.getFirstName(), creditCardDTO.getLastName(), creditCardDTO.getCvv()).orElse(null);
        if (creditCard != null) {
            if (Timestamp.from(Instant.now()).after(creditCard.getExpirationDate()))
                return false;
            List<Part> parts = new ArrayList<>();
            iDs.forEach(id -> parts.add(partRepository.getOne(id)));
            Double total = parts.stream().mapToDouble(Part::getPrice).sum();
            if (total <= creditCard.getBalance()) {
                creditCard.setBalance(creditCard.getBalance() - total);
                creditCardRepository.save(creditCard);

                Transaction transaction = new Transaction();
                transaction.setCard(creditCard);
                transaction.setAmount(total);
                transaction.setUser(u);
                transaction.setParts(parts);
                transactionRepository.save(transaction);
                return true;
            }
        }
        return false;
    }
}
