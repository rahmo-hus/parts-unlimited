package net.croz.unlimited.parts.controller;

import java.time.LocalDateTime;
import java.util.*;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.ExceptionResponse;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.model.warehouse.Part;
import net.croz.unlimited.parts.payload.response.MessageResponse;
import net.croz.unlimited.parts.services.parts.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;

    private static final Logger logger = LoggerFactory.getLogger(PartController.class);

    @ExceptionHandler({NoSuchElementFoundException.class, DuplicateItemException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(RuntimeException exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(exception instanceof  NoSuchElementFoundException ?"NOT_FOUND" : "DUPLICATE_ITEM");
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        logger.error(exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-part")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public Part createPart(@Valid @RequestBody Part part){
        return partService.save(part);
    }

    @GetMapping("/get-all-parts")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> getAllParts(){
        return partService.findAll();
    }

    @GetMapping("/get-part/{serial}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public Part getPartBySerial(@PathVariable(value = "serial") Long serial){
        return partService.findBySerial(serial)
                .orElseThrow(()->new NoSuchElementFoundException("No such element with serial code "+serial));
    }

    @GetMapping("/get-parts/{date}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> getPartByProductionDate(@PathVariable(value = "date")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return partService.findAllByProductionDate(date);
    }

    @GetMapping("/get-part-by/{brandName}/{carName}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> getPartByBrandAndCarName(@PathVariable(value="carName") String carName,
                                               @PathVariable(value = "brandName") String brandName){
        return partService.findByCarsNameAndCarsBrandName(carName, brandName);
    }

    @GetMapping("/get-part-count")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Map<String, String>> getAllPartsByBrandAndCarNameCount() {
        return partService.getAllPartsByCarNameAndBrandName();
    }

    @DeleteMapping("/delete-part/{id}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public MessageResponse deletePart(@PathVariable(value = "id") Long id){
        partService.delete(id);
        return new MessageResponse("Part successfully deleted");
    }


}
