package com.kouforum.backend.dto;

import com.kouforum.backend.models.Token;

public class AuthResponse {

    UserDTO user;

    Token token;

    public void setToken(Token token) {
        this.token = token;
    }


    public Token getToken() {
        return token;
    }


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
}
