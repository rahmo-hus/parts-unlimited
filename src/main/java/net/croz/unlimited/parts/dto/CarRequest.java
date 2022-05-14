package net.croz.unlimited.parts.dto;

import lombok.Data;

import java.util.List;
@Data
public class CarRequest {
    private String name;
    private Long brandId;
    private Double price;
    private String description;
    private String body;
    private Integer year;
    private String uri;
    private String condition;
    private String model;
    private String color;
    private Integer door;
    private String fuel;
    private String transmission;
    private String mileage;
    private List<String> images;
    private List<String> features;
}
