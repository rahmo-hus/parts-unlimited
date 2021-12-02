package net.croz.unlimited.parts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrandDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

}
