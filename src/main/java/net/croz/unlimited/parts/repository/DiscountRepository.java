package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.models.sales.Discount;
import net.croz.unlimited.parts.models.sales.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Repository
public class DiscountRepository {

    private static final String POSTGRES_SAVE_PRODUCT_TO_DISCOUNT ="update sales.product set discount_id=?,\n" +
            "        price= price - (select discount_percentage from sales.discount where id=?)*price/100\n" +
            "where (select start_date from sales.discount where id=?)< CURRENT_DATE\n" +
            "  and (select end_date from sales.discount where id=?) >CURRENT_DATE\n" +
            "  and serial=? and discount_id IS NULL;";
    private static final String POSTGRES_ADD_DISCOUNT = "insert into sales.discount(start_date, end_date, discount_percentage) values ('?','?',?)";
    private static final String POSTGRES_FIND_ALL = "select * from sales.discount";
    private static final String POSTGRES_GET_PRODUCTS_BY_DISCOUNT_ID ="select id, serial, price from sales.product\n" +
            "where sales.product.discount_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional //OK
    public int save(Discount discount){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = simpleDateFormat.format(discount.getStartDate());
        String endDate = simpleDateFormat.format(discount.getEndDate());
        int affectedRows = jdbcTemplate.execute(POSTGRES_ADD_DISCOUNT, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setDate(1, (Date) discount.getStartDate());
                ps.setDate(2, (Date)discount.getEndDate());
                ps.setInt(3, discount.getDiscountPercentage());

                return ps.executeUpdate();
            }
        });
        return affectedRows;
    }

    @Transactional //should be good
    public int saveProductToDiscount(Long productSerial, Long discountId){

        int affectedRows = jdbcTemplate.execute(POSTGRES_SAVE_PRODUCT_TO_DISCOUNT, (PreparedStatementCallback<Integer>) ps -> {
            for(int i=0; i<5; i++)
                ps.setLong(i+1, discountId);
            ps.setLong(6, productSerial);
            Integer rows = ps.executeUpdate();
            return rows;
        });
        return affectedRows;
    }

    @Transactional //maybeeee good
    public List<Discount> findAll(){
        List<Discount> discountList = jdbcTemplate.query(POSTGRES_FIND_ALL, new BeanPropertyRowMapper<>(Discount.class));
        discountList.stream().forEach(discount ->{
            discount.setProducts(
                    jdbcTemplate.execute(POSTGRES_GET_PRODUCTS_BY_DISCOUNT_ID, new PreparedStatementCallback<List<Product>>() {
                        @Override
                        public List<Product> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                            ps.setLong(1, discount.getId());
                            ResultSet resultSet = ps.executeQuery();
                            List<Product> products = new ArrayList<>();
                            while (resultSet.next()){
                                Product product = new Product();
                                product.setId(resultSet.getLong("id"));
                                product.setSerial(resultSet.getLong("serial"));
                                product.setPrice(resultSet.getDouble("price"));
                                products.add(product);
                            }
                            return products;
                        }
                    }));
        } );
        return discountList;
    }
}
