package com.kouforum.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.error.ApiError;
import com.kouforum.backend.models.User;
import com.kouforum.backend.services.UserService;
import com.kouforum.backend.shared.GenericMessage;

import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody User user) {

        ApiError apiError = new ApiError();
        apiError.setPath("/api/users/create");
        apiError.setMessage("Validation error");
        apiError.setStatus(400);
        Map<String, String> validationErrors = new HashMap<>();
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            validationErrors.put("username", "Username cannot be null");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            validationErrors.put("email", "Email cannot be null");
        }
        if (validationErrors.size() > 0) {
            apiError.setValidationErrors(validationErrors);
            return ResponseEntity.badRequest().body(apiError);
        }

        userService.save(user);
        return ResponseEntity.ok(new GenericMessage("User is created."));
    }
}
