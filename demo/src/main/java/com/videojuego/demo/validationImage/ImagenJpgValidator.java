package com.videojuego.demo.validationImage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImagenJpgValidator implements ConstraintValidator<ImagenJpg, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true; // Permitir null o vacío, usa @NotEmpty si es obligatorio
        }
        return value.toLowerCase().endsWith(".jpg");
    }
}
