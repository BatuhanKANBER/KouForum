package com.kouforum.backend.errors;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kouforum.backend.exeptions.ActivationNotificationException;
import com.kouforum.backend.exeptions.AuthenticationException;
import com.kouforum.backend.exeptions.InvalidTokenException;
import com.kouforum.backend.exeptions.NotFoundException;
import com.kouforum.backend.exeptions.NotUniqueEmailException;
import com.kouforum.backend.shared.Messages;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({ MethodArgumentNotValidException.class,
            ActivationNotificationException.class,
            NotUniqueEmailException.class,
            InvalidTokenException.class,
            NotFoundException.class,
            AuthenticationException.class
    })
    ResponseEntity<ApiError> handleException(Exception exception,
            HttpServletRequest httpServletRequest) {
        ApiError apiError = new ApiError();
        apiError.setPath(httpServletRequest.getRequestURI());
        apiError.setMessage(exception.getMessage());
        if (exception instanceof MethodArgumentNotValidException) {
            String message = Messages.getMessageForLocale("kouforum.error.validation", LocaleContextHolder.getLocale());
            apiError.setMessage(message);
            apiError.setStatus(400);
            var validationErrors = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
                            (existing, replacing) -> existing));
            apiError.setValidationErrors(validationErrors);
        } else if (exception instanceof ActivationNotificationException) {
            apiError.setStatus(502);
        } else if (exception instanceof NotUniqueEmailException) {
            apiError.setStatus(400);
            Map<String, String> validationErrors = new HashMap<>();
            validationErrors.put("email", "Email in use");
            apiError.setValidationErrors(validationErrors);
        } else if (exception instanceof InvalidTokenException) {
            apiError.setStatus(400);
        } else if (exception instanceof NotFoundException) {
            apiError.setStatus(404);
        } else if (exception instanceof AuthenticationException) {
            apiError.setStatus(401);
        }
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
