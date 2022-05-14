package net.croz.unlimited.parts.dto;

import lombok.Data;

@Data
public class CarFilterDTO {
    private String body;
    private String condition;
    private String make;
    private String model;
    private String priceRange;
    private String transmission;
    private String year;
}
