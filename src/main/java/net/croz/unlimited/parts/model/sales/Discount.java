package net.croz.unlimited.parts.model.sales;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Discount {
    @Id
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer discountPercentage;
    List<Product> products = new ArrayList<>();
}
