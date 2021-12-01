package net.croz.unlimited.parts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class BrandDTO {

    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
