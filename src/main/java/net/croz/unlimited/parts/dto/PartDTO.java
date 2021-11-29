package net.croz.unlimited.parts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PartDTO {

    @JsonProperty("serial")
    private Long serial;

    @JsonProperty("productionDate")
    private Date productionDate;



}
