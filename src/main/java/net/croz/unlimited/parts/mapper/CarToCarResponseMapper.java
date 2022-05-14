package net.croz.unlimited.parts.mapper;

import net.croz.unlimited.parts.dto.CarResponse;
import net.croz.unlimited.parts.model.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CarToCarResponseMapper {

    public CarResponse convert(Car car){
        List<String> images = new ArrayList<>();
        car.getArtifacts().forEach(artifact -> images.add(artifact.getUri()));

        CarResponse carResponse = new CarResponse();
        carResponse.setName(car.getName());
        carResponse.setBrand(car.getBrand().getName());
        carResponse.setBody(car.getBody());
        carResponse.setColor(car.getColor());
        carResponse.setDoor(car.getDoor());
        carResponse.setFuel(car.getFuel());
        carResponse.setCondition(car.getCondition());
        carResponse.setMileage(car.getMileage());
        carResponse.setModel(car.getModel());
        carResponse.setDescription(car.getDescription());
        carResponse.setId(car.getId());
        carResponse.setYear(car.getYear());
        carResponse.setTransmission(car.getTransmission());
        carResponse.setPrice(car.getPrice());
        carResponse.setFeatures(car.getFeatures() != null ? Arrays.stream(car.getFeatures().split(";")).toList() : null);
        carResponse.setImages(images);

        return carResponse;
    }
}
