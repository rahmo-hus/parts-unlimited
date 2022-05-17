package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> getOneByNumberAndFirstNameAndLastNameAndCvv(String number, String firstName, String lastName, Integer cvv);
}
