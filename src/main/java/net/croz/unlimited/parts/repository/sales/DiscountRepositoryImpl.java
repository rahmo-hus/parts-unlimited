package net.croz.unlimited.parts.repository.sales;

import net.croz.unlimited.parts.models.sales.Discount;
import net.croz.unlimited.parts.models.sales.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DiscountRepositoryImpl implements DiscountRepository {

    private static final String POSTGRES_SAVE_PRODUCT_TO_DISCOUNT ="UPDATE sales.product SET discount_id=?,\n" +
            "        price= price - (SELECT discount_percentage FROM sales.discount WHERE id=?)*price/100\n" +
            "WHERE (SELECT start_date FROM sales.discount WHERE id=?)< CURRENT_DATE\n" +
            "  AND (SELECT end_date FROM sales.discount WHERE id=?) >CURRENT_DATE\n" +
            "  AND serial=? AND discount_id IS NULL;";
    private static final String POSTGRES_ADD_DISCOUNT = "INSERT INTO sales.discount(start_date, end_date, discount_percentage) VALUES (?,?,?)";
    private static final String POSTGRES_FIND_ALL = "SELECT * FROM sales.discount";
    private static final String POSTGRES_GET_PRODUCTS_BY_DISCOUNT_ID ="SELECT sales.product.id, sales.product.serial, price, production_date FROM sales.product "
    +"INNER JOIN part p ON p.serial=sales.product.serial WHERE sales.product.discount_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int save(Discount discount){
        Integer affectedRows = jdbcTemplate.execute(POSTGRES_ADD_DISCOUNT, (PreparedStatementCallback<Integer>) ps -> {
            ps.setDate(1, discount.getStartDate());
            ps.setDate(2, discount.getEndDate());
            ps.setInt(3, discount.getDiscountPercentage());
            return ps.executeUpdate();
        });
        return affectedRows;
    }

    @Override
    @Transactional
    public int saveProductToDiscount(Long productSerial, Long discountId){

        Integer affectedRows = jdbcTemplate.execute(POSTGRES_SAVE_PRODUCT_TO_DISCOUNT, (PreparedStatementCallback<Integer>) ps -> {
            for(int i=0; i<4; i++)
                ps.setLong(i+1, discountId);
            ps.setLong(5, productSerial);
            Integer rows = ps.executeUpdate();
            return rows;
        });
        return affectedRows;
    }

    @Override
    @Transactional
    public List<Discount> findAll(){
        List<Discount> discountList = jdbcTemplate.query(POSTGRES_FIND_ALL, new BeanPropertyRowMapper<>(Discount.class));
        discountList.forEach(discount ->{
            discount.setProducts(
                    jdbcTemplate.execute(POSTGRES_GET_PRODUCTS_BY_DISCOUNT_ID, new PreparedStatementCallback<>() {
                        @Override
                        public List<Product> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                            ps.setLong(1, discount.getId());
                            ResultSet resultSet = ps.executeQuery();
                            List<Product> products = new ArrayList<>();
                            while (resultSet.next()) {
                                Product product = new Product();
                                product.setId(resultSet.getLong("id"));
                                product.setSerial(resultSet.getLong("serial"));
                                product.setPrice(resultSet.getDouble("price"));
                                product.setProductionDate(resultSet.getDate("production_date"));
                                products.add(product);
                            }
                            return products;
                        }
                    }));
        } );
        return discountList;
    }
}
