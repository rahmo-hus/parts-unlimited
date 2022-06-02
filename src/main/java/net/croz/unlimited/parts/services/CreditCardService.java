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
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;
    private final PartRepository partRepository;


    public Boolean purchase(CreditCardDTO creditCardDTO) {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CreditCard creditCard = creditCardRepository.getOneByNumberAndFirstNameAndLastNameAndCvv(creditCardDTO.getNumber(), creditCardDTO.getFirstName(), creditCardDTO.getLastName(), creditCardDTO.getCvv()).orElse(null);
        if (creditCard != null) {
            if (Timestamp.from(Instant.now()).after(creditCard.getExpirationDate()))
                return false;
            List<Part> parts = new ArrayList<>();
            AtomicReference<Double> total = new AtomicReference<>(0.0);
            creditCardDTO.getCartInfoRequests().forEach(id -> {
                Part part = partRepository.getOne(id.getId());
                part.setQuantity(part.getQuantity() - id.getQuantity().intValue());
                parts.add(part);
                total.updateAndGet(v -> v + part.getPrice() * id.getQuantity());
            });
            if (total.get() <= creditCard.getBalance()) {
                creditCard.setBalance(creditCard.getBalance() - total.get());
                creditCardRepository.save(creditCard);
                partRepository.saveAll(parts);

                Transaction transaction = new Transaction();
                transaction.setAmount(total.get());
                transaction.setDate(new Timestamp(new Date().getTime()));
                transaction.setUser(u);
                transaction.setQuantity(creditCardDTO.getCartInfoRequests().stream().mapToInt(item-> Math.toIntExact(item.getQuantity())).sum());
                transaction.setParts(parts);
                transactionRepository.save(transaction);
                return true;
            }
        }
        return false;
    }
}
