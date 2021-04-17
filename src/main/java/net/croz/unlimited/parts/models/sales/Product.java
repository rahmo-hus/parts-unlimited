package net.croz.unlimited.parts.models.sales;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "sales",name = "product",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "serial")})
public class Product {
    @Id
    private Long id;
    private Long serial;

    private Double price;

    //private Discount discount;

    public Product(Long serial, Double price){
        this.serial = serial;
        this.price = price;
    }
}
