package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByName(String name);
    List<Car> findAllByBodyAndConditionAndBrandNameAndTransmissionAndPriceBetween(String body, String condition, String brand, String transmission, Double priceBottom, Double priceUpper);
    List<Car> findAllByPriceBetween(Double priceBottom, Double priceUpper);
}
