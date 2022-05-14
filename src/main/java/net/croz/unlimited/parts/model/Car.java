package net.croz.unlimited.parts.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rating;
    private Double price;
    private String body;
    private Integer year;
    private String description;
    private String condition;
    private String model;
    private String fuel;
    private Integer door;
    private String transmission;
    private String color;
    private String mileage;
    private String features;
    @ManyToOne()
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    @OneToMany(mappedBy = "car")
    private List<Artifact> artifacts;

}
