package org.example.osahaneatspringboot.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.example.osahaneatspringboot.annotation.PasswordMatches;
import org.example.osahaneatspringboot.constant.message.AccountErrorMessage;
import org.example.osahaneatspringboot.dto.request.RegisterRequest;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext context) {
        boolean isValid = request.getPassword() != null && request.getPassword().equals(request.getConfirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(AccountErrorMessage.PASSWORDS_NOT_MATCH)
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
