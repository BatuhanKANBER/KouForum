package com.kouforum.backend.services;

import com.kouforum.backend.dto.Credentials;
import com.kouforum.backend.models.Token;
import com.kouforum.backend.models.User;

public interface TokenService {

    public Token createToken(User user, Credentials creds);

    public User verifyToken(String authorizationHeader);

    public void logout(String authorizationHeader);
}
