package net.croz.unlimited.parts.services.parts;

import net.croz.unlimited.parts.models.warehouse.Part;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PartService {
    Part save(Part part);
    List<Part> findAll();
    Optional<Part> findBySerial(Long serial);
    List<Part> findAllByProductionDate(Date date);
    List<Part> findByCarsNameAndCarsBrandName(String carName, String brandName);
    List<Map<String,String>> getAllPartsByCarNameAndBrandName();
    void delete(Long id);
}
