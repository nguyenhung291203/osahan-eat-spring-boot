package org.example.osahaneatspringboot.dto.request;

import static org.example.osahaneatspringboot.constant.message.AccountErrorMessage.*;
import static org.example.osahaneatspringboot.constant.regexp.AccountRegexPatterns.PHONE_NUMBER_PATTERN;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.example.osahaneatspringboot.annotation.PasswordMatches;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@PasswordMatches
@Builder
public class RegisterRequest {
    @Pattern(regexp = PHONE_NUMBER_PATTERN, message = USERNAME_INVALID)
    @NotBlank(message = USERNAME_EMPTY)
    String username;

    @Size(min = 6, message = PASSWORD_SIZE)
    @NotBlank(message = PASSWORD_EMPTY)
    String password;

    @Size(min = 6, message = PASSWORD_SIZE)
    @NotBlank(message = PASSWORD_EMPTY)
    String confirmPassword;

    @Size(min = 6, message = FULLNAME_SIZE)
    @NotBlank(message = FULLNAME_NOT_BLANK)
    String fullName;

    boolean gender = true;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate dateOfBirth;

    String address;

    String email;

    String roleId;
}
