package com.kouforum.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kouforum.backend.dto.AuthResponse;
import com.kouforum.backend.dto.Credentials;
import com.kouforum.backend.dto.UserDTO;
import com.kouforum.backend.exeptions.AuthenticationException;
import com.kouforum.backend.models.Token;
import com.kouforum.backend.models.User;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    public AuthResponse authenticate(Credentials creds) {
        User inDB = userService.findByEmail(creds.email());
        if (inDB == null) {
            throw new AuthenticationException();
        }
        if (!passwordEncoder.matches(creds.password(), inDB.getPassword())) {
            throw new AuthenticationException();
        }
        if (inDB.isActive() == false) {
            throw new AuthenticationException();
        }

        Token token = tokenService.createToken(inDB, creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDB));
        return authResponse;
    }

    public void logout(String authorizationHeader) {
        tokenService.logout(authorizationHeader);
    }

}
