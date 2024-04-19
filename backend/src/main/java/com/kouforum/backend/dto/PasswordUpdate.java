package com.kouforum.backend.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PasswordUpdate(
        @Size(min = 6, max = 255) @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).*$", message = "{kouforum.constraint.password.pattern}") String password) {

}
