package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.PartRequest;
import net.croz.unlimited.parts.dto.PartResponse;
import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.mapper.PartRequestToPartMapper;
import net.croz.unlimited.parts.mapper.PartToPartResponseMapper;
import net.croz.unlimited.parts.model.Brand;
import net.croz.unlimited.parts.model.Car;
import net.croz.unlimited.parts.model.Part;
import net.croz.unlimited.parts.repository.BrandRepository;
import net.croz.unlimited.parts.repository.CarRepository;
import net.croz.unlimited.parts.repository.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final PartRequestToPartMapper partRequestToPartMapper;
    private final PartToPartResponseMapper partToPartResponseMapper;

    public PartResponse save(PartRequest partRequest) throws ParseException {
        Part part = partRequestToPartMapper.convert(partRequest);
        List<Car> cars = new ArrayList<>();
        partRequest.getCarIds().forEach(id-> cars.add(carRepository.getOne(id)));
        part.setCars(cars);

        Part savedPart = partRepository.save(part);

        return partToPartResponseMapper.convert(savedPart);
    }

    public List<PartResponse> findAll() {
        List<Part> parts = partRepository.findAll();
        List<PartResponse> partResponses = new ArrayList<>();
        parts.forEach(p-> partResponses.add(partToPartResponseMapper.convert(p)));

        return partResponses;
    }

    public List<PartResponse> findAllByCategory(String category){
        List<Part> parts = partRepository.findAllByCategory(category);
        List<PartResponse> partResponses = new ArrayList<>();
        parts.forEach(p-> partResponses.add(partToPartResponseMapper.convert(p)));

        return partResponses;
    }

    public PartResponse getOne(Long id){
        return partToPartResponseMapper.convert(partRepository.getOne(id));
    }

    @Transactional
    public Optional<Part> findBySerial(Long serial) {
        return partRepository.findBySerial(serial);
    }

    @Transactional
    public List<Part> findAllByProductionDate(Date date) {
        return partRepository.findAllByProductionDate(date);
    }

    @Transactional
    public List<Part> findByCarsNameAndCarsBrandName(String carName, String brandName) {
        return partRepository.findByCarsNameAndCarsBrandName(carName, brandName);
    }

    @Transactional
    public List<Map<String, String>> getAllPartsByCarNameAndBrandName() {
        List<Map<String,String>> brandsAndAutomobiles = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        cars.forEach(car -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("brand_and_automobile",car.getBrand().getName()+" "+car.getName());
            hashMap.put("count", partRepository.findByCarsNameAndCarsBrandName(car.getName(), car.getBrand().getName()).size()+"");
            brandsAndAutomobiles.add(hashMap);
        });
        return brandsAndAutomobiles;
    }

    @Transactional
    public void delete(Long id) {
        Part part = partRepository.findById(id).orElseThrow(()-> new NoSuchElementFoundException("No element with Id "+id));
        partRepository.delete(part);
    }
}
