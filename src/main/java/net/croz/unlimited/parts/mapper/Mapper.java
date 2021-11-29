package net.croz.unlimited.parts.mapper;

import net.croz.unlimited.parts.dto.PartDTO;
import net.croz.unlimited.parts.model.warehouse.Part;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    PartDTO partToPartDTO(Part part);


}
