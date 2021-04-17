package net.croz.unlimited.parts.controllers;

import java.time.LocalDateTime;
import java.util.*;

import net.croz.unlimited.parts.exceptions.ExceptionResponse;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.models.entities.Part;
import net.croz.unlimited.parts.repository.PartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parts")
public class PartController {
    @Autowired
    PartRepository partRepository;

    private static final Logger logger = LoggerFactory.getLogger(PartController.class);

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(NoSuchElementFoundException exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        logger.error("Element not found: {}",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-part")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public Part createPart(@RequestBody Part part){
        Part savedPart = partRepository.save(part);
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
    public Part getPartByProductionDate(@PathVariable(value = "date")
                                            @DateTimeFormat(pattern = "dd-MM-yyyy") Date date){
        var requiredPart = partRepository.findByProductionDate(date)
                .orElseThrow(()->new NoSuchElementFoundException("No such element with date "+date.toString()));
        return requiredPart;
    }

    @GetMapping("/get-part-by/{brandName}/{carName}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> getPartByBrandAndCarName(@PathVariable(value="carName") String carName,
                                               @PathVariable(value = "brandName") String brandName){
        var requiredPart = partRepository.findByCarsNameAndCarsBrandName(carName, brandName);
        return requiredPart;
    }

    @GetMapping("/get-part-count/{brandName}/{carName}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public Map<String, String> getPartByBrandAndCarNameCount(@PathVariable(value="carName") String carName,
                                                             @PathVariable(value = "brandName") String brandName) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("brand_and_automobile", brandName+" "+carName);
        hashMap.put("count", partRepository.findByCarsNameAndCarsBrandName(carName,brandName).size()+"");
        return  hashMap;
    }

    @DeleteMapping("/delete-part/{id}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public Map<String, Boolean> deletePart(@PathVariable(value = "id") Long id){
        var part = partRepository.findById(id).orElseThrow(()-> new NoSuchElementFoundException("No element with Id "+id));
        Map<String, Boolean> response = new HashMap<>();
        partRepository.delete(part);
        response.put("deleted",Boolean.TRUE);
        return response;
    }


}
