package net.croz.unlimited.parts.models.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import net.croz.unlimited.parts.models.entities.Brand;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public Car(String name, Brand brand){
        this.name = name;
        this.brand = brand;
    }

}