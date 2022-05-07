package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.CarRequestDTO;
import net.croz.unlimited.parts.dto.CarResponseDTO;
import net.croz.unlimited.parts.model.Car;
import net.croz.unlimited.parts.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public List<CarResponseDTO> findAll(){
        List<Car> cars = carRepository.findAll();
        List<CarResponseDTO> carResponseDTOS = new ArrayList<>();

        cars.forEach(car -> carResponseDTOS.add(modelMapper.map(car, CarResponseDTO.class)));

        return carResponseDTOS;
    }

    public CarResponseDTO getOne(Long id){
        Car car = carRepository.getOne(id);

        return modelMapper.map(car, CarResponseDTO.class);
    }

    public CarResponseDTO save(CarResponseDTO carResponseDTO){
        Car car = modelMapper.map(carResponseDTO, Car.class);
        carRepository.save(car);

        return carResponseDTO;
    }
}
