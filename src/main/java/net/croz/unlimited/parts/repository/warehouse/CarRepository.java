package net.croz.unlimited.parts.repository.warehouse;

import net.croz.unlimited.parts.model.warehouse.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByName(String name);
}
