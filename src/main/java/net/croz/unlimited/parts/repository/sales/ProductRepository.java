package net.croz.unlimited.parts.repository.sales;

import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.models.sales.Product;
import net.croz.unlimited.parts.repository.warehouse.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class ProductRepository{

    private static final String POSTGRES_INSERT = "INSERT INTO sales.product(serial, price) SELECT * FROM ( VALUES (?,?)) AS p(newS, newP)" +
            "WHERE EXISTS(SELECT FROM part pa WHERE pa.serial=p.newS);";
    private static final String POSTGRES_SELECT_ALL = "SELECT sales.product.id, sales.product.serial, price, production_date " +
            "FROM sales.product INNER JOIN part p ON sales.product.serial=p.serial";
    private static final String POSTGRES_UPDATE = "UPDATE sales.product SET price=? WHERE ID=?";
    private static final String POSTGRES_DELETE = "DELETE FROM sales.product WHERE id=?";
    private static final String POSTGRES_FIND_BY_ID="SELECT * from sales.product WHERE id=?";

    @Autowired
    PartRepository partRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public int save(Product product){
        final PreparedStatementCreator preparedStatementCreator = con -> {
            final PreparedStatement preparedStatement = con
                    .prepareStatement(POSTGRES_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, product.getSerial());
            preparedStatement.setDouble(2, product.getPrice());
            return preparedStatement;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows=jdbcTemplate.update(preparedStatementCreator, keyHolder);
        product.setId(keyHolder.getKey().longValue());
        return affectedRows;
    }

    @Transactional
    public List<Product> getProducts(){
        return jdbcTemplate.query(POSTGRES_SELECT_ALL, new BeanPropertyRowMapper<>(Product.class));
    }

    @Transactional
    public int delete(Long id){

        Integer affectedRows = jdbcTemplate.execute(POSTGRES_DELETE, (PreparedStatementCallback<Integer>) ps -> {
            int success = 0;
            ps.setLong(1, id);
            success = ps.executeUpdate();
            return success;
        });
        return affectedRows;
    }

    @Transactional
    public Product changePrice(Long id, Double price){
        final PreparedStatementCreator preparedStatementCreator = connection ->{
            final PreparedStatement preparedStatement = connection.prepareStatement(POSTGRES_UPDATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, price);
            preparedStatement.setLong(2, id);
            return preparedStatement;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update(preparedStatementCreator, keyHolder);
        List<Product> products = jdbcTemplate.query(POSTGRES_FIND_BY_ID, new BeanPropertyRowMapper(Product.class));
        return products.get(0);
    }
}
