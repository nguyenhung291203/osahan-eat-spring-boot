package org.example.osahaneatspringboot.exception;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ValidateException extends RuntimeException {
    Map<String, String> errors;

    public ValidateException(Map<String, String> errors) {
        this.errors = errors;
    }
}
