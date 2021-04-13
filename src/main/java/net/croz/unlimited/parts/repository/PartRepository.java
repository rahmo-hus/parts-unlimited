package net.croz.unlimited.parts.repository;


import net.croz.unlimited.parts.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    //Optional<Part> findBySerial(String serial);
    //Optional<Part> findByProductionDate(Date date);
    //Optional<Part> findByBrandAndCarName(String name);
}
