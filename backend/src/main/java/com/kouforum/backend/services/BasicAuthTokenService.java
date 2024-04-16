package com.kouforum.backend.services;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.kouforum.backend.dto.Credentials;
import com.kouforum.backend.models.Token;
import com.kouforum.backend.models.User;

@Service
public class BasicAuthTokenService implements TokenService {

    @Override
    public Token createToken(User user, Credentials creds) {
        String emailClonePassword = creds.email() + ":" + creds.password();
        String token = Base64.getEncoder().encodeToString(emailClonePassword.getBytes());
        return new Token("Basic", token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        // TODO Auto-generated method stub
        return null;
    }

}
