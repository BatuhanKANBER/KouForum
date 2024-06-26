package com.kouforum.backend.services;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kouforum.backend.dto.Credentials;
import com.kouforum.backend.models.Token;
import com.kouforum.backend.models.User;

@Service
@ConditionalOnProperty(name = "kouforum.token-type", havingValue = "basic")
public class BasicAuthTokenService implements TokenService {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Token createToken(User user, Credentials creds) {
        String emailClonePassword = creds.email() + ":" + creds.password();
        String token = Base64.getEncoder().encodeToString(emailClonePassword.getBytes());
        return new Token("Basic", token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if (authorizationHeader == null) {
            return null;
        }
        var base64Encoded = authorizationHeader.split("Basic ")[1];
        var decoded = new String(Base64.getDecoder().decode(base64Encoded));
        var credentials = decoded.split(":");
        var email = credentials[0];
        var password = credentials[1];
        User inDB = userService.findByEmail(email);
        if (inDB == null) {
            return null;
        }
        if (!passwordEncoder.matches(password, inDB.getPassword())) {
            return null;
        }
        return inDB;
    }

    @Override
    public void logout(String authorizationHeader) {
    }
}
