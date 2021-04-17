package net.croz.unlimited.parts.mappers;

import net.croz.unlimited.parts.models.sales.Discount;
import net.croz.unlimited.parts.models.sales.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setSerial(rs.getLong("serial"));
        product.setPrice(rs.getDouble("price"));
        //product.setDiscount(rs.getObject(4, Discount.class));

        return product;

    }
}