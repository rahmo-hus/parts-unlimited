package net.croz.unlimited.parts.mapper;

import net.croz.unlimited.parts.dto.PartRequest;
import net.croz.unlimited.parts.model.Part;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class PartRequestToPartMapper {

    public Part convert(PartRequest partRequest) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Part part = new Part();
        part.setImage(partRequest.getImage());
        part.setName(partRequest.getName());
        part.setType(partRequest.getType());
        part.setManufacturer(partRequest.getManufacturer());
        part.setBrand(partRequest.getBrand());
        part.setQuantity(partRequest.getQuantity());
        part.setCategory(partRequest.getCategory());
        part.setCode(partRequest.getCode());
        part.setDescription(partRequest.getDescription());
        part.setSerial(partRequest.getSerial());
        part.setPrice(partRequest.getPrice());
        part.setProductionDate(format.parse(partRequest.getProductionDate()));

        return part;
    }
}
