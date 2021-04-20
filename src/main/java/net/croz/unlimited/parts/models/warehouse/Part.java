package net.croz.unlimited.parts.models.warehouse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long serial;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "car_part",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name="car_id"))
    private List<Car> cars = new ArrayList<>();

    public Part(Long serial, Date productionDate){
        this.serial = serial;
        this.productionDate = productionDate;
    }

}
