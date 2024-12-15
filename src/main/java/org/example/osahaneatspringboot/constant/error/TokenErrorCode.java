package org.example.osahaneatspringboot.constant.error;

import org.example.osahaneatspringboot.constant.message.TokenErrorMessage;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TokenErrorCode implements BaseErrorCode {
    TOKEN_NOT_FOUND(401, TokenErrorMessage.TOKEN_NOT_FOUND, HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_EXPIRED(401, TokenErrorMessage.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(401, TokenErrorMessage.INVALID_TOKEN, HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(401, TokenErrorMessage.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED),
    UNSUPPORTED_TOKEN(401, TokenErrorMessage.UNSUPPORTED_TOKEN, HttpStatus.UNAUTHORIZED),
    EMPTY_TOKEN(401, TokenErrorMessage.EMPTY_TOKEN, HttpStatus.UNAUTHORIZED),
    EMPTY_REVOKED(401, TokenErrorMessage.EMPTY_REVOKED, HttpStatus.UNAUTHORIZED);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
