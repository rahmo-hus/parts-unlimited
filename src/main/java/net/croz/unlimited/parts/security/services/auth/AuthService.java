package net.croz.unlimited.parts.security.services.auth;

import net.croz.unlimited.parts.dto.LoginRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequestDTO loginRequest);
}
