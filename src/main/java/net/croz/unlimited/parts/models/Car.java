package net.croz.unlimited.parts.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="car")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    /*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    @Transient
    private Set<Part> carParts = new HashSet<>();*/

    public Car(String name, Brand brand){
        this.name = name;
        this.brand = brand;
    }

}
