package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.mappers.ProductMapper;
import net.croz.unlimited.parts.models.sales.Discount;
import net.croz.unlimited.parts.models.sales.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Repository
public class DiscountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional //OK
    public int save(Discount discount){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = simpleDateFormat.format(discount.getStartDate());
        String endDate = simpleDateFormat.format(discount.getEndDate());
        int rows = jdbcTemplate.update("insert into sales.discount(start_date, end_date, discount_percentage) values ('" + startDate + "','"
                + endDate + "'," + discount.getDiscountPercentage() + ")");
        return rows;
    }

    @Transactional //not good
    public int saveProductToDiscount(Long productId, List<Long> serialNumbers){
        int rows = jdbcTemplate.update("");
        return rows;
    }

    @Transactional //maybeeee good
    public List<Discount> findAll(){
        List<Discount> discountList = jdbcTemplate.query("select * from sales.discount", new BeanPropertyRowMapper<>(Discount.class));
        discountList.stream().forEach(discount ->{
            discount.setProducts(
                    jdbcTemplate.query("select id, serial, price from sales.product\n" +
                            "left join sales.product_discount pd on product.id = pd.product_id\n" +
                            "where pd.discount_id="+discount.getId()+";", new BeanPropertyRowMapper<>(Product.class)));
        } );
        return discountList;
    }
}
