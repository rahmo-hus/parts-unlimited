package net.croz.unlimited.parts.repository;

import java.sql.*;
import  java.util.List;

import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.mappers.ProductMapper;
import net.croz.unlimited.parts.models.sales.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepository{

    private static final String POSTGRES_INSERT = "insert into sales.product(serial, price) SELECT * FROM ( values (?,?)) as p(newS, newP)where exists(select from part pa where pa.serial=p.newS);";
    private static final String POSTGRES_SELECT_ALL = "SELECT * FROM sales.product";
    private static final String POSTGRES_UPDATE = "UPDATE sales.product SET price=? WHERE ID=?";
    private static final String POSTGRES_DELETE = "DELETE FROM sales.product WHERE id=?";

    @Autowired
    PartRepository partRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public int save(Product product){
        boolean added;
             added = jdbcTemplate.execute(POSTGRES_INSERT, new PreparedStatementCallback<Boolean>() {
                @Override
                public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    ps.setLong(1, product.getSerial());
                    ps.setDouble(2, product.getPrice());
                    boolean success=false;
                    try {
                        success = ps.execute();
                    }
                    catch (Throwable e){
                        throw new DuplicateItemException("Product with serial "+product.getSerial()+" already exists.");
                    }
                    if(!success) throw new NoSuchElementFoundException("Cannot add product. There is no part with serial "+product.getSerial());
                    return true;
                }
            });
             return added ? 1 :0;

    }

    @Transactional
    public List<Product> getProducts(){
        return jdbcTemplate.query(POSTGRES_SELECT_ALL, new BeanPropertyRowMapper<>(Product.class));
    }

    @Transactional
    public int delete(Long id){

        int affectedRows = jdbcTemplate.execute(POSTGRES_DELETE, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1,id);
                return ps.executeUpdate();
            }
        });
        return affectedRows;
    }

    @Transactional
    public int changePrice(Long id, Double price){
        int affectedRows = jdbcTemplate.execute(POSTGRES_UPDATE, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setDouble(1, price);
                ps.setLong(2, id);

                return ps.executeUpdate();
            }
        });
        return affectedRows;
    }
}
