package org.example.osahaneatspringboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import org.example.osahaneatspringboot.validator.TrimWhitespaceValidator;

@Constraint(validatedBy = TrimWhitespaceValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TrimWhitespace {
    String message() default "Trường không được chứa khoảng trắng thừa";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
