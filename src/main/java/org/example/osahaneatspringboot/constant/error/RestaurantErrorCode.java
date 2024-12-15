package org.example.osahaneatspringboot.constant.error;

import java.util.Map;

import org.example.osahaneatspringboot.constant.message.RestaurantErrorMessage;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum RestaurantErrorCode implements BaseErrorCode {
    TITLE_REQUIRED(422, RestaurantErrorMessage.TITLE_REQUIRED, HttpStatus.UNPROCESSABLE_ENTITY),
    ADDRESS_REQUIRED(422, RestaurantErrorMessage.ADDRESS_REQUIRED, HttpStatus.UNPROCESSABLE_ENTITY),
    TITLE_DUPLICATE(409, RestaurantErrorMessage.TITLE_DUPLICATE, HttpStatus.CONFLICT),
    ADDRESS_DUPLICATE(409, RestaurantErrorMessage.ADDRESS_DUPLICATE, HttpStatus.CONFLICT),
    IMAGE_REQUIRED(422, RestaurantErrorMessage.IMAGE_REQUIRED, HttpStatus.UNPROCESSABLE_ENTITY),
    RESTAURANT_NOT_ACTIVE(403, RestaurantErrorMessage.RESTAURANT_NOT_ACTIVE, HttpStatus.FORBIDDEN),
    RESTAURANT_NOT_FOUND(404, RestaurantErrorMessage.RESTAURANT_NOT_FOUND, HttpStatus.NOT_FOUND),
    INVALID_OPEN_TIME(422, RestaurantErrorMessage.INVALID_OPEN_TIME, HttpStatus.UNPROCESSABLE_ENTITY),
    INVALID_CLOSE_TIME(422, RestaurantErrorMessage.INVALID_CLOSE_TIME, HttpStatus.UNPROCESSABLE_ENTITY),
    CLOSE_BEFORE_OPEN(422, RestaurantErrorMessage.CLOSE_BEFORE_OPEN, HttpStatus.UNPROCESSABLE_ENTITY),
    LATITUDE_REQUIRED(422, RestaurantErrorMessage.LATITUDE_REQUIRED, HttpStatus.UNPROCESSABLE_ENTITY),
    LONGITUDE_REQUIRED(422, RestaurantErrorMessage.LONGITUDE_REQUIRED, HttpStatus.UNPROCESSABLE_ENTITY),
    INVALID_LATITUDE(422, RestaurantErrorMessage.INVALID_LATITUDE, HttpStatus.UNPROCESSABLE_ENTITY),
    INVALID_LONGITUDE(422, RestaurantErrorMessage.INVALID_LONGITUDE, HttpStatus.UNPROCESSABLE_ENTITY),
    UNEXPECTED_ERROR(500, RestaurantErrorMessage.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatus httpStatus;

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
