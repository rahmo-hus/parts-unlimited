package net.croz.unlimited.parts.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    private String description;
    private String category;
    private String brand;
    private String code;
    private String name;
    private String type;
    private String manufacturer;
    private String image;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private Discount discount;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "car_part",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars = new ArrayList<>();

    public Part(Long serial, Date productionDate) {
        this.serial = serial;
        this.productionDate = productionDate;
    }

}
