package net.croz.unlimited.parts.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import net.croz.unlimited.parts.dto.BrandDTO;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rating;
    private Double price;
    private String body;
    private Integer year;

    @ManyToOne()
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public Car(String name, Brand brand){
        this.name = name;
        this.brand = brand;
    }

}
