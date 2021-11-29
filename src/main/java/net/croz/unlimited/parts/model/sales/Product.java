package net.croz.unlimited.parts.model.sales;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class Product {
    @Id
    private Long id;
    private Long serial;
    private Double price;
    private Date productionDate;
    public Product(Long serial, Double price){
        this.serial = serial;
        this.price = price;
    }
}
