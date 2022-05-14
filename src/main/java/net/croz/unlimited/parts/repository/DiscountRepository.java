package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount save(Discount discount);

    @Query(nativeQuery = true, value = "UPDATE part SET discount_id=:discount_id," +
            "price= price - (SELECT discount_percentage FROM discount WHERE id=:discount_id)*price/100 " +
            "WHERE (SELECT start_date FROM discount WHERE id=:discount_id)< CURRENT_DATE" +
            "  AND (SELECT end_date FROM discount WHERE id=:discount_id) >CURRENT_DATE" +
            "  AND serial=:serial AND discount_id IS NULL")
    int saveProductToDiscount(@Param("serial")Long productSerial, @Param("discount_id") Long discountId);

    List<Discount> findAll();
}
