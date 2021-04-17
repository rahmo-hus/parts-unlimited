package net.croz.unlimited.parts.mappers;
import net.croz.unlimited.parts.models.sales.Discount;
import net.croz.unlimited.parts.models.sales.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountMapper implements RowMapper<Discount> {

    @Override
    public Discount mapRow(ResultSet rs, int rowNum) throws SQLException {

        Discount discount = new Discount();
        discount.setId(rs.getLong("id"));
        discount.setStartDate(rs.getDate("start_date"));
        discount.setEndDate(rs.getDate("end_date"));
        discount.setDiscountPercentage(rs.getInt("discount_percentage"));

        //extract tomorrow

        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setSerial(rs.getLong("serial"));
        product.setPrice(rs.getDouble("price"));
        //product.setDiscount(rs.getObject(4, Discount.class));

        return discount;

    }
}