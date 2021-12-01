package net.croz.unlimited.parts.security.services;

import net.croz.unlimited.parts.dto.LoginRequestDTO;
import net.croz.unlimited.parts.dto.RegistrationRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequestDTO loginRequest);
    ResponseEntity<?> registerUser(RegistrationRequestDTO registrationRequestDTO);
}
