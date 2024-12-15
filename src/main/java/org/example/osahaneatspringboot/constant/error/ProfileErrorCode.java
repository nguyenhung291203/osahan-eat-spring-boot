package org.example.osahaneatspringboot.constant.error;

import org.example.osahaneatspringboot.constant.message.ProfileErrorMessage;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProfileErrorCode implements BaseErrorCode {
    PROFILE_NOT_FOUND(4001, ProfileErrorMessage.PROFILE_NOT_FOUND, HttpStatus.NOT_FOUND),
    PROFILE_UPDATE_FAILED(4002, ProfileErrorMessage.PROFILE_UPDATE_FAILED, HttpStatus.BAD_REQUEST),
    INVALID_PROFILE_DATA(4003, ProfileErrorMessage.INVALID_PROFILE_DATA, HttpStatus.BAD_REQUEST),
    PROFILE_EXISTED(4004, ProfileErrorMessage.PROFILE_EXISTED, HttpStatus.BAD_REQUEST),
    IMAGE_EMPTY(4005, ProfileErrorMessage.IMAGE_EMPTY, HttpStatus.BAD_REQUEST),
    IMAGE_LOAD_FAILED(4006, ProfileErrorMessage.IMAGE_LOAD_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_TOO_LARGE(4007, ProfileErrorMessage.IMAGE_TOO_LARGE, HttpStatus.BAD_REQUEST),
    INVALID_IMAGE_FORMAT(4008, ProfileErrorMessage.INVALID_IMAGE_FORMAT, HttpStatus.BAD_REQUEST);

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
