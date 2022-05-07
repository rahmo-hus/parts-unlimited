package net.croz.unlimited.parts.controller;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.CarResponseDTO;
import net.croz.unlimited.parts.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars/")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping(value = "save")
    @PreAuthorize("hasRole('SALES') or hasRole('CUSTOMER')")
    public ResponseEntity<CarResponseDTO> save(CarResponseDTO carResponseDTO){
        CarResponseDTO car = carService.save(carResponseDTO);

        return ResponseEntity.ok(car);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('SALES') or hasRole('CUSTOMER')")
    public ResponseEntity<List<CarResponseDTO>> findAll(){
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SALES') or hasRole('CUSTOMER')")
    public ResponseEntity<CarResponseDTO> getOne(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(carService.getOne(id));
    }
}
