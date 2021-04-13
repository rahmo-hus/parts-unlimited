package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
