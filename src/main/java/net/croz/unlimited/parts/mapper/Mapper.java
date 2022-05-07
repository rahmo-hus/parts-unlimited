package net.croz.unlimited.parts.mapper;

import net.croz.unlimited.parts.dto.BrandDTO;
import net.croz.unlimited.parts.dto.CarResponseDTO;
import net.croz.unlimited.parts.dto.PartDTO;
import net.croz.unlimited.parts.model.Brand;
import net.croz.unlimited.parts.model.Car;
import net.croz.unlimited.parts.model.Part;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    PartDTO partToPartDTO(Part part);

    Part partDTOToPart(PartDTO partDTO);

    CarResponseDTO carToCarDTO(Car car);

    Car carDTOToCar(CarResponseDTO carResponseDTO);

    BrandDTO brandToBrandDTO(Brand brand);

    Brand brandDTOToBrand(BrandDTO brandDTO);


}
