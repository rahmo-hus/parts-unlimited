package net.croz.unlimited.parts.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class CreditCard {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String number;
    private String firstName;
    private String lastName;
    private String type;
    private Timestamp expirationDate;
    private Integer cvv;
    private Double balance;
    private Boolean isEnabled;
}
