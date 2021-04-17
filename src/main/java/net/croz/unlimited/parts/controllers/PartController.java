package net.croz.unlimited.parts.controllers;

import java.time.LocalDateTime;
import java.util.*;

import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.ExceptionResponse;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.models.entities.Car;
import net.croz.unlimited.parts.models.entities.Part;
import net.croz.unlimited.parts.payload.response.MessageResponse;
import net.croz.unlimited.parts.repository.CarRepository;
import net.croz.unlimited.parts.repository.PartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/parts")
public class PartController {
    @Autowired
    PartRepository partRepository;

    @Autowired
    CarRepository carRepository;


    private static final Logger logger = LoggerFactory.getLogger(PartController.class);

    @ExceptionHandler({NoSuchElementFoundException.class, DuplicateItemException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(RuntimeException exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(exception instanceof  NoSuchElementFoundException ?"NOT_FOUND" : "DUPLICATE_ITEM");
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        //logger.error("Element not found: {}",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-part")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public Part createPart(@Valid @RequestBody Part part){
        Part savedPart;
        try {
            savedPart = partRepository.save(part);
        }
        catch (Throwable e){
            throw new DuplicateItemException("Part with serial already exists.");
        }
        return savedPart;
    }

    @GetMapping("/get-all-parts")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> getAllParts(){
        var parts = partRepository.findAll();
        return parts;
    }

    @GetMapping("/get-part/{serial}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public Part getPartBySerial(@PathVariable(value = "serial") Long serial){
        var requiredPart = partRepository.findBySerial(serial)
                .orElseThrow(()->new NoSuchElementFoundException("No such element with serial code "+serial));
        return requiredPart;
    }

    @GetMapping("/get-parts/{date}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> getPartByProductionDate(@PathVariable(value = "date")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        var requiredParts = partRepository.findAllByProductionDate(date);
        return requiredParts;
    }

    @GetMapping("/get-part-by/{brandName}/{carName}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> getPartByBrandAndCarName(@PathVariable(value="carName") String carName,
                                               @PathVariable(value = "brandName") String brandName){
        var requiredParts = partRepository.findByCarsNameAndCarsBrandName(carName, brandName);
        return requiredParts;
    }

    @GetMapping("/get-part-count")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Map<String, String>> getPartByBrandAndCarNameCount() {
        List<Map<String,String>> brandsAndAutomobiles = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        cars.forEach(car -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("brand_and_automobile",car.getBrand().getName()+" "+car.getName());
            hashMap.put("count", partRepository.findByCarsNameAndCarsBrandName(car.getName(), car.getBrand().getName()).size()+"");
            brandsAndAutomobiles.add(hashMap);
        });
        return brandsAndAutomobiles;
    }

    @DeleteMapping("/delete-part/{id}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public MessageResponse deletePart(@PathVariable(value = "id") Long id){
        var part = partRepository.findById(id).orElseThrow(()-> new NoSuchElementFoundException("No element with Id "+id));
        Map<String, Boolean> response = new HashMap<>();
        partRepository.delete(part);
        return new MessageResponse("Part successfully deleted");
    }


}
