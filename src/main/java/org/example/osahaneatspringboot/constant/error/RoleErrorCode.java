package org.example.osahaneatspringboot.constant.error;

import org.example.osahaneatspringboot.constant.message.RoleErrorMessage;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum RoleErrorCode implements BaseErrorCode {
    ROLE_NOT_FOUND(404, RoleErrorMessage.ROLE_NOT_FOUND, HttpStatus.NOT_FOUND),
    ROLE_ALREADY_EXISTS(422, RoleErrorMessage.ROLE_ALREADY_EXISTS, HttpStatus.CONFLICT),
    INVALID_ROLE_NAME(400, RoleErrorMessage.INVALID_ROLE_NAME, HttpStatus.BAD_REQUEST),
    ROLE_ASSIGNMENT_FAILED(500, RoleErrorMessage.ROLE_ASSIGNMENT_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    ROLE_ACCESS_DENIED(403, RoleErrorMessage.ROLE_ACCESS_DENIED, HttpStatus.FORBIDDEN);

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
