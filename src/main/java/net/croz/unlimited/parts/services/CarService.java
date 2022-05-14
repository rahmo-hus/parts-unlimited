package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.CarFilterDTO;
import net.croz.unlimited.parts.dto.CarRequest;
import net.croz.unlimited.parts.dto.CarResponse;
import net.croz.unlimited.parts.mapper.CarRequestToCarMapper;
import net.croz.unlimited.parts.mapper.CarToCarResponseMapper;
import net.croz.unlimited.parts.model.Artifact;
import net.croz.unlimited.parts.model.Brand;
import net.croz.unlimited.parts.model.Car;
import net.croz.unlimited.parts.repository.ArtifactRepository;
import net.croz.unlimited.parts.repository.BrandRepository;
import net.croz.unlimited.parts.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BrandRepository  brandRepository;
    private final ArtifactRepository artifactRepository;
    private final CarToCarResponseMapper carToCarResponseMapper;
    private final CarRequestToCarMapper carRequestToCarMapper;

    public List<CarResponse> findAll(){
        List<Car> cars = carRepository.findAll();
        List<CarResponse> carResponses = new ArrayList<>();

        cars.forEach(car -> carResponses.add(carToCarResponseMapper.convert(car)));

        return carResponses;
    }

    public CarResponse getOne(Long id){
        Car car = carRepository.getOne(id);

        return carToCarResponseMapper.convert(car);
    }

    public void delete(Long id){
        carRepository.deleteById(id);
    }

    public void save(CarRequest carRequest){
        Car car = carRequestToCarMapper.convert(carRequest);
        Brand brand = brandRepository.getOne(carRequest.getBrandId());
        List<Artifact> artifacts = new ArrayList<>();
        car.setBrand(brand);
        Car savedCar = carRepository.save(car);
        carRequest.getImages().forEach(i -> artifacts.add(new Artifact(i, savedCar)));
        car.setArtifacts(artifactRepository.saveAll(artifacts));
    }

    public List<CarResponse> filter(CarFilterDTO carFilterDTO){
        String priceBottom = carFilterDTO.getPriceRange().split("-")[0].trim().replace("$", "") , priceUpper = carFilterDTO.getPriceRange().split("-")[1].trim().replace("$", "");
        List<Car> cars = carRepository.findAllByBodyAndConditionAndBrandNameAndTransmissionAndPriceBetween(carFilterDTO.getBody(), carFilterDTO.getCondition(), carFilterDTO.getMake(), carFilterDTO.getTransmission(), Double.parseDouble(priceBottom.replace(",",".")), Double.parseDouble(priceUpper.replace(",", ".")));
        List<CarResponse> carResponses = new ArrayList<>();
        cars.forEach(car -> carResponses.add(carToCarResponseMapper.convert(car)));

        return carResponses;
    }
}
