package net.croz.unlimited.parts.mapper;

import net.croz.unlimited.parts.dto.BrandDTO;
import net.croz.unlimited.parts.dto.CarDTO;
import net.croz.unlimited.parts.dto.PartDTO;
import net.croz.unlimited.parts.model.warehouse.Brand;
import net.croz.unlimited.parts.model.warehouse.Car;
import net.croz.unlimited.parts.model.warehouse.Part;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    PartDTO partToPartDTO(Part part);

    Part partDTOToPart(PartDTO partDTO);

    CarDTO carToCarDTO(Car car);

    Car carDTOToCar(CarDTO carDTO);

    BrandDTO brandToBrandDTO(Brand brand);

    Brand brandDTOToBrand(BrandDTO brandDTO);


}
