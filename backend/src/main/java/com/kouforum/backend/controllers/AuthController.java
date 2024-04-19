package com.kouforum.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.dto.AuthResponse;
import com.kouforum.backend.dto.Credentials;
import com.kouforum.backend.services.AuthService;
import com.kouforum.backend.shared.GenericMessage;

import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    ResponseEntity<AuthResponse> handleAuthentication(@Valid @RequestBody Credentials creds) {
        var authResponse = authService.authenticate(creds);
        var cookie = ResponseCookie.from("kouforum-token", authResponse.getToken().getToken()).path("/").httpOnly(true)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(authResponse);
    }

    @PostMapping("/logout")
    ResponseEntity<GenericMessage> handleLogout(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @CookieValue(name = "kouforum-token", required = false) String cookieValue) {

        var tokenWithPrefix = authorizationHeader;
        if (cookieValue != null) {
            tokenWithPrefix = "AnyPrefix " + cookieValue;
        }
        authService.logout(tokenWithPrefix);
        var cookie = ResponseCookie.from("kouforum-token", "").path("/").maxAge(0).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new GenericMessage("Logout success."));
    }
}
