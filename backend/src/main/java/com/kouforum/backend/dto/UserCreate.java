package com.kouforum.backend.dto;

import com.kouforum.backend.models.User;
import com.kouforum.backend.validations.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(

        @NotBlank @Size(min = 4, max = 30) String username,

        @NotBlank @Email @UniqueEmail String email,

        @Size(min = 6, max = 255) @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).*$", message = "{kouforum.constraint.password.pattern}") String password) {

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
