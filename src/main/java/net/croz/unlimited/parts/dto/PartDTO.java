package net.croz.unlimited.parts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class PartDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("serial")
    private Long serial;

    @JsonProperty("productionDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date productionDate;

    @JsonProperty("cars")
    private List<CarDTO> cars;

}
