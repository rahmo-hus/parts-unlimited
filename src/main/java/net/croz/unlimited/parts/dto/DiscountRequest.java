package net.croz.unlimited.parts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountRequest {
    private String startDate;
    private String endDate;
    private Integer discountPercentage;
    private Long partId;
}
