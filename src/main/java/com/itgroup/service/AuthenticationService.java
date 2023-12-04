package com.itgroup.service;

import com.itgroup.dto.AuthenticationRequest;
import com.itgroup.dto.AuthenticationResponse;
import com.itgroup.dto.RegisterRequest;
import com.itgroup.models.Role;
import com.itgroup.models.User;
import com.itgroup.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .first_name(request.getFirstname())
                .last_name(request.getLastname())
                .phone_number(request.getPhoneNumber())
                .email(request.getEmail())
                .gender(null)
                .birth_day(null)
                .role_id(2)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
