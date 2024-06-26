package net.croz.unlimited.parts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO {
    private String number;
    private String firstName;
    private String lastName;
    private Integer cvv;
    private String expirationDate;
    List<CartInfoRequest> cartInfoRequests;
}
