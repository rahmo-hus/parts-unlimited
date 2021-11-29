package net.croz.unlimited.parts.services.parts;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.exceptions.DuplicateItemException;
import net.croz.unlimited.parts.exceptions.NoSuchElementFoundException;
import net.croz.unlimited.parts.model.warehouse.Brand;
import net.croz.unlimited.parts.model.warehouse.Car;
import net.croz.unlimited.parts.model.warehouse.Part;
import net.croz.unlimited.parts.repository.warehouse.BrandRepository;
import net.croz.unlimited.parts.repository.warehouse.CarRepository;
import net.croz.unlimited.parts.repository.warehouse.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Part save(Part part) {
        List<Car> cars = part.getCars();
        cars.forEach(car -> {
            Brand brand = car.getBrand();
            Optional<Brand> existingBrand = brandRepository.findByName(brand.getName());
            if(existingBrand.isPresent())
                car.setBrand(existingBrand.get());
            else
                car.setBrand(brandRepository.save(brand));
            Optional<Car> existingCar = carRepository.findByName(car.getName());
            if(existingCar.isPresent())
                car.setId(existingCar.get().getId());
            else
                carRepository.save(car);
        });
        try {
            return partRepository.save(part);
        }
        catch (Throwable e){
            throw new DuplicateItemException("Error: Part with serial "+part.getSerial()+" already exists.");
        }
    }

    @Override
    @Transactional
    public List<Part> findAll() {
        return partRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Part> findBySerial(Long serial) {
        return partRepository.findBySerial(serial);
    }

    @Override
    @Transactional
    public List<Part> findAllByProductionDate(Date date) {
        return partRepository.findAllByProductionDate(date);
    }

    @Override
    @Transactional
    public List<Part> findByCarsNameAndCarsBrandName(String carName, String brandName) {
        return partRepository.findByCarsNameAndCarsBrandName(carName, brandName);
    }

    @Override
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

    @Override
    @Transactional
    public void delete(Long id) {
        Part part = partRepository.findById(id).orElseThrow(()-> new NoSuchElementFoundException("No element with Id "+id));
        partRepository.delete(part);
    }
}
