package com.kouforum.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdate(@NotBlank @Size(min = 4, max = 30) String username) {

}