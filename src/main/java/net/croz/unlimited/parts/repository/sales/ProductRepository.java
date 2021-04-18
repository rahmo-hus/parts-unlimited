package net.croz.unlimited.parts.repository.sales;

import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.models.sales.Product;
import net.croz.unlimited.parts.repository.entities.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository{

    private static final String POSTGRES_INSERT = "INSERT INTO sales.product(serial, price) SELECT * FROM ( VALUES (?,?)) AS p(newS, newP)" +
            "WHERE EXISTS(SELECT FROM part pa WHERE pa.serial=p.newS);";
    private static final String POSTGRES_SELECT_ALL = "SELECT sales.product.id, sales.product.serial, price, production_date " +
            "FROM sales.product INNER JOIN part p ON sales.product.serial=p.serial";
    private static final String POSTGRES_UPDATE = "UPDATE sales.product SET price=? WHERE ID=?";
    private static final String POSTGRES_DELETE = "DELETE FROM sales.product WHERE id=?";

    @Autowired
    PartRepository partRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public int save(Product product){
        Integer added;
             added = jdbcTemplate.execute(POSTGRES_INSERT, (PreparedStatementCallback<Integer>) ps -> {
                 ps.setLong(1, product.getSerial());
                 ps.setDouble(2, product.getPrice());
                 int success;
                 try {
                     success = ps.executeUpdate();
                 }
                 catch (Throwable e){
                     throw new DuplicateItemException("Product with serial "+product.getSerial()+" already exists.");
                 }
                 if(success == 0) throw new NoSuchElementFoundException("Cannot add product. There is no part with serial "+product.getSerial());
                 return success;
             });
             return  added;

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
    public int changePrice(Long id, Double price){
        Integer affectedRows = jdbcTemplate.execute(POSTGRES_UPDATE, (PreparedStatementCallback<Integer>) ps -> {
            ps.setDouble(1, price);
            ps.setLong(2, id);
            return ps.executeUpdate();
        });
        return affectedRows;
    }
}
