package net.croz.unlimited.parts.repository;

import java.sql.*;
import  java.util.List;

import net.croz.unlimited.parts.mappers.ProductMapper;
import net.croz.unlimited.parts.models.sales.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepository{

    private static final String POSTGRES_INSERT = "INSERT INTO sales.product(serial, price) VALUES (?,?)";
    private static final String POSTGRES_SELECT_ALL = "SELECT * FROM sales.product";
    private static final String POSTGRES_UPDATE = "UPDATE sales.product SET price=? WHERE ID=?";
    private static final String POSTGRES_FIND_BY_SERIAL="SELECT * FROM sales.product WHERE serial=?";

    final KeyHolder keyHolder = new GeneratedKeyHolder();

    @Autowired
    PartRepository partRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public int save(Product product){
      //TODO: IMPLEMENT SAVE
        return 1;

    }

    @Transactional
    public List<Product> getProducts(){
        return jdbcTemplate.query(POSTGRES_SELECT_ALL, new ProductMapper());
    }

    @Transactional
    public int update(Long id, Double newPrice){
        Object[] values = new Object[]{newPrice, id};
        return jdbcTemplate.update(POSTGRES_UPDATE, values);
    }

    @Transactional
    public List<Product> findBySerial(Long serial){
        List<Product> products= jdbcTemplate.query(POSTGRES_FIND_BY_SERIAL,new Object[]{serial},
                new BeanPropertyRowMapper<Product>(Product.class));
        return products;
    }

    @Transactional
    public Product findById(Long id){
        Product product = null;
        try {
            product = jdbcTemplate.queryForObject("SELECT * FROM sales.product WHERE id=?",
                    new Object[]{id}, new ProductMapper());
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        return product;
    }

    @Transactional
    public int delete(Long id){
        return jdbcTemplate.update("DELETE FROM sales.product WHERE id=?", id);
    }

    @Transactional
    public int changePrice(Long id, Double price){
        return jdbcTemplate.update("update sales.product set price=? where id=?", price, id);
    }
}
