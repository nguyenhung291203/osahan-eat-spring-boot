package org.example.osahaneatspringboot.constant.error;

import java.util.Map;

import org.example.osahaneatspringboot.constant.message.FavoriteErrorMessage;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum FavoriteErrorCode implements BaseErrorCode {
    FAVORITE_EXISTED(422, FavoriteErrorMessage.FAVORITE_EXISTED, HttpStatus.CONFLICT),
    FAVORITE_NOT_EXISTED(404, FavoriteErrorMessage.FAVORITE_NOT_EXISTED, HttpStatus.NOT_FOUND),
    FAVORITE_LIMIT_REACHED(400, FavoriteErrorMessage.FAVORITE_LIMIT_REACHED, HttpStatus.BAD_REQUEST),
    FAVORITE_ALREADY_ADDED(422, FavoriteErrorMessage.FAVORITE_ALREADY_ADDED, HttpStatus.CONFLICT),
    FAVORITE_NOT_ADDED(500, FavoriteErrorMessage.FAVORITE_NOT_ADDED, HttpStatus.INTERNAL_SERVER_ERROR),
    FAVORITE_REMOVAL_FAILED(500, FavoriteErrorMessage.FAVORITE_REMOVAL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    FAVORITE_INVALID_ID(400, FavoriteErrorMessage.FAVORITE_INVALID_ID, HttpStatus.BAD_REQUEST),
    FAVORITE_EMPTY(400, FavoriteErrorMessage.FAVORITE_EMPTY, HttpStatus.BAD_REQUEST),
    FAVORITE_OPERATION_FAILED(500, FavoriteErrorMessage.FAVORITE_OPERATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

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

    public String getDetailedMessage(Map<String, String> additionalInfo) {
        StringBuilder detailedMessage = new StringBuilder(message);
        if (additionalInfo != null && !additionalInfo.isEmpty()) {
            detailedMessage.append(": ");
            additionalInfo.forEach((key, value) ->
                    detailedMessage.append(key).append(" - ").append(value).append("; "));
        }
        return detailedMessage.toString();
    }
}
