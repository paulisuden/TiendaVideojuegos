package com.videojuego.demo.validationImage;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImagenJpgValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ImagenJpg {
    String message() default "La imagen debe terminar en .jpg";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
