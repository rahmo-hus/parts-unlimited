package net.croz.unlimited.parts.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class TransactionResponse {
    private Long id;
    private Double amount;
    private String info;
    private Timestamp date;
    private Integer quantity;
    private UserDTO user;
    private List<PartResponse> parts;
}
