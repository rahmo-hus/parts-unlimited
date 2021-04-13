package net.croz.unlimited.parts.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="part")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serial;
    private Date productionDate;
    //TODO: Auto ima dijelove, dijelovi imaju auta, auta imaju dijelove...
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "car_part",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name="car_id"))
    private Set<Car> cars = new HashSet<>();

    public Part(String serial, Date productionDate){
        this.serial = serial;
        this.productionDate = productionDate;
    }

}
