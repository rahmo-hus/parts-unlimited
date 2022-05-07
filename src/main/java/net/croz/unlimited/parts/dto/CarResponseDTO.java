package net.croz.unlimited.parts.dto;

import lombok.Data;

@Data
public class CarResponseDTO {

    private Long id;
    private String name;
    private BrandDTO brand;
    private String rating;
    private Double price;
    private String body;
    private Integer year;
    private String uri;
}
