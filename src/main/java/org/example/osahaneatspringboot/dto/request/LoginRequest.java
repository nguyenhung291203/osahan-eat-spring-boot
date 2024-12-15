package org.example.osahaneatspringboot.dto.request;

import static org.example.osahaneatspringboot.constant.message.AccountErrorMessage.*;
import static org.example.osahaneatspringboot.constant.regexp.AccountRegexPatterns.PHONE_NUMBER_PATTERN;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @Pattern(regexp = PHONE_NUMBER_PATTERN, message = USERNAME_INVALID)
    @NotBlank(message = USERNAME_EMPTY)
    String username;

    @Size(min = 6, message = PASSWORD_SIZE)
    @NotBlank(message = PASSWORD_NOT_BLANK)
    String password;
}
