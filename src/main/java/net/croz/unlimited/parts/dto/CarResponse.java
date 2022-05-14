package net.croz.unlimited.parts.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarResponse {

    private Long id;
    private String name;
    private String brand;
    private Double price;
    private String body;
    private Integer year;
    private String uri;
    private String condition;
    private String model;
    private String color;
    private String description;
    private Integer door;
    private String fuel;
    private String transmission;
    private String mileage;
    private List<String> images;
    private List<String> features;
}
