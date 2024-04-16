package com.kouforum.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.dto.AuthResponse;
import com.kouforum.backend.dto.Credentials;
import com.kouforum.backend.services.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/check")
    AuthResponse handleAuthentication(@Valid @RequestBody Credentials creds) {
        return authService.authenticate(creds);
    }



}
