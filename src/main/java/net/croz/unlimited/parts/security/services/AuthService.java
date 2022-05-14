package net.croz.unlimited.parts.security.services;

import net.croz.unlimited.parts.dto.LoginRequestDTO;
import net.croz.unlimited.parts.dto.RegistrationRequestDTO;
import net.croz.unlimited.parts.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequestDTO loginRequest);
    ResponseEntity<?> registerUser(RegistrationRequestDTO registrationRequestDTO);
    UserDTO whoAmI(HttpServletRequest request);
}
