package net.croz.unlimited.parts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequestDTO {

    @JsonProperty("username")
    @NotBlank
    private String username;

    @NotBlank
    @JsonProperty("password")
    private String password;


}