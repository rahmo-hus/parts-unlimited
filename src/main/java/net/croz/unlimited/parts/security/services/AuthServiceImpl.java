package net.croz.unlimited.parts.security.services;

import liquibase.pro.packaged.E;
import liquibase.pro.packaged.R;
import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.LoginRequestDTO;
import net.croz.unlimited.parts.dto.JwtResponseDTO;
import net.croz.unlimited.parts.dto.MessageResponse;
import net.croz.unlimited.parts.dto.RegistrationRequestDTO;
import net.croz.unlimited.parts.model.users.ERole;
import net.croz.unlimited.parts.model.users.Role;
import net.croz.unlimited.parts.model.users.User;
import net.croz.unlimited.parts.repository.RoleRepository;
import net.croz.unlimited.parts.repository.UserRepository;
import net.croz.unlimited.parts.security.jwt.JwtUtils;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponseDTO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(RegistrationRequestDTO registrationRequest) {
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account`
        User user = new User(registrationRequest.getUsername(),
                passwordEncoder.encode(registrationRequest.getPassword()),
                        registrationRequest.getEmail(),
                        registrationRequest.getFirstName(),
                        registrationRequest.getLastName());

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(ERole.ROLE_CUSTOMER);
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}