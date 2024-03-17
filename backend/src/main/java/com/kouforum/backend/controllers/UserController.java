package com.kouforum.backend.controllers;
import java.util.HashMap;
import java.util.stream.Collectors;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.dto.UserCreate;
import com.kouforum.backend.errors.ApiError;
import com.kouforum.backend.exeptions.ActivationNotificationExeption;
import com.kouforum.backend.exeptions.NotUniqueEmailExeption;
import com.kouforum.backend.services.UserService;
import com.kouforum.backend.shared.GenericMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    //USER CREATE
    @PostMapping("/create")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {
        userService.save(user.toUser());
        return new GenericMessage("User is created.");
    }

    //VALID OPERATIONS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/users/create");
        apiError.setMessage("Validation error");
        apiError.setStatus(400);
        var validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacing)-> existing));
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    //VALID FOR ACTIVATION EMAIL
    @ExceptionHandler(ActivationNotificationExeption.class)
    ResponseEntity<ApiError> handleActivationNotificationEx(ActivationNotificationExeption exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/users/create");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(502);
        return ResponseEntity.status(502).body(apiError);
    }

    //VALID FOR UNIQUE EMAIL
    @ExceptionHandler(NotUniqueEmailExeption.class)
    ResponseEntity<ApiError> handleNotUniqueEmailEx(NotUniqueEmailExeption exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/users/create");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);
        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("email", "Email in use");
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.status(400).body(apiError);
    }
}
