package net.croz.unlimited.parts.dto;

import lombok.Data;

import java.util.List;

@Data
public class PartResponse {
    private Long id;
    private Long serial;
    private String productionDate;
    private String brand;
    private String name;
    private String type;
    private String code;
    private String manufacturer;
    private String category;
    private String image;
    private String description;
    private Double price;
    private List<CarResponse> cars;
    private DiscountResponse discount;
}
