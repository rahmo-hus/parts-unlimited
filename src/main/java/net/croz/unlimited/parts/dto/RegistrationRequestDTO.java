package net.croz.unlimited.parts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationRequestDTO {

    @JsonProperty("firstName")
    @NotBlank(message = "First name is required")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @JsonProperty("username")
    @Size(min = 5, message = "Min 3 characters are required")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    @Size(min = 8, message = "Minimum 8 characters are required")
    private String password;

    @JsonProperty("repeatedPassword")
    @Size(min = 8, message = "Minimum 8 characters are required")
    private String repeatedPassword;

}
