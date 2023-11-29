package com.itgroup.controllers;

import com.itgroup.dto.AuthenticationRequest;
import com.itgroup.dto.AuthenticationResponse;
import com.itgroup.dto.RegisterRequest;
import com.itgroup.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthenticationService service;

    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> registration( @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login( @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/logout")
    public String logout() {
        return null;
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id) {
        return null;
    }
}
