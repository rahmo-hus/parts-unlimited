package net.croz.unlimited.parts.repository;

import java.util.List;

import net.croz.unlimited.parts.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findBySerial(Long serial);
    List<Part> findAllByProductionDate(Date date);
    List<Part> findByCarsNameAndCarsBrandName(String carName, String brandName);
    List<Part> findAllByCategory(String category);
}
