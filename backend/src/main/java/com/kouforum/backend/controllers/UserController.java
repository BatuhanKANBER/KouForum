package com.kouforum.backend.controllers;
import java.util.HashMap;
import java.util.stream.Collectors;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.dto.UserCreate;
import com.kouforum.backend.models.User;
import com.kouforum.backend.errors.ApiError;
import com.kouforum.backend.exeptions.ActivationNotificationExeption;
import com.kouforum.backend.exeptions.InvalidTokenExeption;
import com.kouforum.backend.exeptions.NotUniqueEmailExeption;
import com.kouforum.backend.services.UserService;
import com.kouforum.backend.shared.GenericMessage;
import com.kouforum.backend.shared.Messages;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    //USER CREATE
    @PostMapping("/create")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {
        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("kouforum.create.user.success", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/{token}/activate")
    GenericMessage activateUser(@PathVariable String token){
        userService.activateUser(token);
        String message = Messages.getMessageForLocale("kouforum.activate.user.success", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @GetMapping("/list")
    public Page<User> getUsers(Pageable page) {
        return userService.getUsers(page);
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

    //INVALID ACTIVATION TOKEN
    @ExceptionHandler(InvalidTokenExeption.class)
    ResponseEntity<ApiError> handleInvalidActivationTokenEx(InvalidTokenExeption exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/users/create");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);
        return ResponseEntity.status(400).body(apiError);
    }
}
