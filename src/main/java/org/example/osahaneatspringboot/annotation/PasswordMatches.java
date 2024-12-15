package org.example.osahaneatspringboot.annotation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import org.example.osahaneatspringboot.constant.message.AccountErrorMessage;
import org.example.osahaneatspringboot.validator.PasswordMatchesValidator;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String message() default AccountErrorMessage.PASSWORDS_NOT_MATCH;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
