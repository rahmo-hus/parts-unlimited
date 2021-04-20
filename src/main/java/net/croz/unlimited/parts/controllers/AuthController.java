package net.croz.unlimited.parts.controllers;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.payload.request.LoginRequest;
import net.croz.unlimited.parts.payload.response.JwtResponse;
import net.croz.unlimited.parts.repository.users.RoleRepository;
import net.croz.unlimited.parts.repository.users.UserRepository;
import net.croz.unlimited.parts.security.jwt.JwtUtils;
import net.croz.unlimited.parts.security.services.auth.AuthService;
import net.croz.unlimited.parts.security.services.auth.AuthServiceImpl;
import net.croz.unlimited.parts.security.services.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }


}
