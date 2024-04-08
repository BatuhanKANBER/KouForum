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
import com.kouforum.backend.dto.UserDTO;
import com.kouforum.backend.errors.ApiError;
import com.kouforum.backend.exeptions.ActivationNotificationException;
import com.kouforum.backend.exeptions.InvalidTokenException;
import com.kouforum.backend.exeptions.NotFoundException;
import com.kouforum.backend.exeptions.NotUniqueEmailException;
import com.kouforum.backend.services.UserService;
import com.kouforum.backend.shared.GenericMessage;
import com.kouforum.backend.shared.Messages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    // USER CREATE
    @PostMapping("/create")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {
        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("kouforum.create.user.success", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/{token}/activate")
    GenericMessage activateUser(@PathVariable String token) {
        userService.activateUser(token);
        String message = Messages.getMessageForLocale("kouforum.activate.user.success",
                LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @GetMapping("/list")
    public Page<UserDTO> getUsers(Pageable page) {
        return userService.getUsers(page).map(UserDTO::new);
    }

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id) {
        return new UserDTO(userService.getUser(id));
    }

    // VALID OPERATIONS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgNotValidException(MethodArgumentNotValidException exception,
            HttpServletRequest httpServletRequest) {
        ApiError apiError = new ApiError();
        apiError.setPath(httpServletRequest.getRequestURI());
        apiError.setMessage("Validation error");
        apiError.setStatus(400);
        var validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
                        (existing, replacing) -> existing));
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    // VALID FOR ACTIVATION EMAIL
    @ExceptionHandler(ActivationNotificationException.class)
    ResponseEntity<ApiError> handleActivationNotificationException(ActivationNotificationException exception,
            HttpServletRequest httpServletRequest) {
        ApiError apiError = new ApiError();
        apiError.setPath(httpServletRequest.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(502);
        return ResponseEntity.status(502).body(apiError);
    }

    // VALID FOR UNIQUE EMAIL
    @ExceptionHandler(NotUniqueEmailException.class)
    ResponseEntity<ApiError> handleNotUniqueEmailException(NotUniqueEmailException exception,
            HttpServletRequest httpServletRequest) {
        ApiError apiError = new ApiError();
        apiError.setPath(httpServletRequest.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);
        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("email", "Email in use");
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.status(400).body(apiError);
    }

    // INVALID ACTIVATION TOKEN
    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<ApiError> handleInvalidActivationTokenException(InvalidTokenException exception,
            HttpServletRequest httpServletRequest) {
        ApiError apiError = new ApiError();
        apiError.setPath(httpServletRequest.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);
        return ResponseEntity.status(400).body(apiError);
    }

    // INVALID ENTITY
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception,
            HttpServletRequest httpServletRequest) {
        ApiError apiError = new ApiError();
        apiError.setPath(httpServletRequest.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(404);
        return ResponseEntity.status(404).body(apiError);
    }
}
