package net.croz.unlimited.parts.mapper;

import net.croz.unlimited.parts.dto.CarRequest;
import net.croz.unlimited.parts.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarRequestToCarMapper {

    public Car convert(CarRequest request) {
        Car car = new Car();
        car.setName(request.getName());
        car.setBody(request.getBody());
        car.setDescription(request.getDescription());
        car.setCondition(request.getCondition());
        car.setDoor(request.getDoor());
        car.setFeatures(String.join(";", request.getFeatures()));
        car.setColor(request.getColor());
        car.setFuel(request.getFuel());
        car.setMileage(request.getMileage());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
        car.setTransmission(request.getTransmission());

        return car;
    }
}
