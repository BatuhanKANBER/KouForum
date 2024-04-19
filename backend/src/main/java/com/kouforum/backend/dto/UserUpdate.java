package com.kouforum.backend.dto;

import com.kouforum.backend.validations.FileType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdate(
        @NotBlank @Size(min = 4, max = 30) String username,

        @FileType(types = {
                "jpeg", "png", "jpg" }) String image){
}