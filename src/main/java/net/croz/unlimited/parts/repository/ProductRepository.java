package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.models.sales.Product;
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

    private static final String POSTGRES_INSERT = "insert into sales.product(serial, price) SELECT * FROM ( values (?,?)) as p(newS, newP)where exists(select from part pa where pa.serial=p.newS);";
    private static final String POSTGRES_SELECT_ALL = "SELECT sales.product.id, sales.product.serial, price, production_date FROM sales.product inner join part p on sales.product.serial=p.serial";
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
                    boolean success;
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
