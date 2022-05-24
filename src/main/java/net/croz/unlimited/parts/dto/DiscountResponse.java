package net.croz.unlimited.parts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountResponse {
    private Long id;
    private String startDate;
    private String endDate;
    private Integer discountPercentage;
    private Boolean active;
}
