package net.croz.unlimited.parts.security.services.auth;

import net.croz.unlimited.parts.payload.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
}
