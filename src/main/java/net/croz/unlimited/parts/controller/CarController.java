package net.croz.unlimited.parts.controller;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.CarFilterDTO;
import net.croz.unlimited.parts.dto.CarRequest;
import net.croz.unlimited.parts.dto.CarResponse;
import net.croz.unlimited.parts.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/cars/")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping(value = "save")
    @PreAuthorize("hasRole('SALES') or hasRole('CUSTOMER')")
    public ResponseEntity<Void> save(@RequestBody CarRequest carRequest){
        carService.save(carRequest);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('SALES') or hasRole('CUSTOMER')")
    public ResponseEntity<List<CarResponse>> findAll(){
        return ResponseEntity.ok(carService.findAll());
    }

    @PostMapping("/filter")
    public ResponseEntity<List<CarResponse>> filter(@RequestBody CarFilterDTO carFilterDTO){
        return ResponseEntity.ok(carService.filter(carFilterDTO));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SALES') or hasRole('CUSTOMER')")
    public ResponseEntity<CarResponse> getOne(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(carService.getOne(id));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SALES')")
    public ResponseEntity<Void> delete(@PathVariable(name="id") Long id){
        carService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
