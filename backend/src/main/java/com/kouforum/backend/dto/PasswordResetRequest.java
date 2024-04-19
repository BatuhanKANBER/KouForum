package com.kouforum.backend.dto;

import jakarta.validation.constraints.Email;

public record PasswordResetRequest(@Email String email) {
} 
