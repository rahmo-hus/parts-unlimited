package net.croz.unlimited.parts.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.croz.unlimited.parts.models.entities.Car;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long serial;
    private Date productionDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "car_part",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name="car_id"))
    private Set<Car> cars = new HashSet<>();

    public Part(Long serial, Date productionDate){
        this.serial = serial;
        this.productionDate = productionDate;
    }

}
