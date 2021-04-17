package net.croz.unlimited.parts.models.sales;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "sales", name="discount")
public class Discount {
    @Id
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer discountPercentage;
    @OneToMany(fetch = FetchType.LAZY)
            @JoinColumn(name = "discount_id")
    List<Product> products = new ArrayList<>();
}
