package net.croz.unlimited.parts.dto;

import lombok.Data;

import java.util.List;

@Data
public class PartRequest {

    private Long serial;
    private String productionDate;
    private String brand;
    private String code;
    private String manufacturer;
    private String name;
    private String type;
    private String image;
    private Integer quantity;
    private Double price;
    private String description;
    private String category;
    private List<Long> carIds;
}
