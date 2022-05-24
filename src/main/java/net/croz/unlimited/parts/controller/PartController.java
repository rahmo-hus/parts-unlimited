package net.croz.unlimited.parts.controller;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.MessageResponse;
import net.croz.unlimited.parts.dto.PartRequest;
import net.croz.unlimited.parts.dto.PartResponse;
import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.ExceptionResponse;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.model.Part;
import net.croz.unlimited.parts.services.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;
    private static final Logger logger = LoggerFactory.getLogger(PartController.class);
    @ExceptionHandler({NoSuchElementFoundException.class, DuplicateItemException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(RuntimeException exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(exception instanceof NoSuchElementFoundException ? "NOT_FOUND" : "DUPLICATE_ITEM");
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        logger.error(exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<PartResponse>> findAll() {
        return ResponseEntity.ok(partService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<PartResponse> save(@Valid @RequestBody PartRequest partRequest) throws ParseException {
        return ResponseEntity.ok(partService.save(partRequest));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<PartResponse>> getByCategory(@PathVariable String category){
        return ResponseEntity.ok(partService.findAllByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartResponse> getOne(@PathVariable Long id){
        return ResponseEntity.ok(partService.getOne(id));
    }

    @GetMapping("/get/{serial}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public Part getPartBySerial(@PathVariable(value = "serial") Long serial) {
        return partService.findBySerial(serial)
                .orElseThrow(() -> new NoSuchElementFoundException("No such element with serial code " + serial));
    }

    @GetMapping("/get-parts/{date}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> findAllByProductionDate(@PathVariable(value = "date")
                                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return partService.findAllByProductionDate(date);
    }

    @GetMapping("/get-part-by/{brandName}/{carName}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Part> getPartByBrandAndCarName(@PathVariable(value = "carName") String carName,
                                               @PathVariable(value = "brandName") String brandName) {
        return partService.findByCarsNameAndCarsBrandName(carName, brandName);
    }

    @GetMapping("/get-part-count")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public List<Map<String, String>> getAllPartsByBrandAndCarNameCount() {
        return partService.getAllPartsByCarNameAndBrandName();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('WAREHOUSE')")
    public ResponseEntity<Void> deletePart(@PathVariable(value = "id") Long id) {
        partService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
