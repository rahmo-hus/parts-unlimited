package net.croz.unlimited.parts.controllers;

import java.util.List;

import net.croz.unlimited.parts.models.Car;
import net.croz.unlimited.parts.models.Part;
import net.croz.unlimited.parts.repository.CarRepository;
import net.croz.unlimited.parts.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parts")
public class PartController {
    @Autowired
    PartRepository partRepository;

    @Autowired
    CarRepository carRepository;

    @GetMapping("/get-all-parts")
    public List<Part> getAllParts(){
        var parts = partRepository.findAll();
        return parts;
    }
    @GetMapping("/get-all-cars")
    public List<Car> getAllCars(){
        var cars = carRepository.findAll();
        return cars;
    }
}
