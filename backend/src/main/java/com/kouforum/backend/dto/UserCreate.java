package com.kouforum.backend.dto;

import com.kouforum.backend.models.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(

        @NotBlank @Size(min = 4, max = 30) String username,

        @NotBlank @Email String email,

        @Size(min = 6, max = 255) @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).*$", message = "{hoaxify.constraint.password.pattern}") String password) {

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
