package org.example.osahaneatspringboot.constant.error;

import java.util.Map;

import org.example.osahaneatspringboot.constant.message.CartItemErrorMessage;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CartItemErrorCode implements BaseErrorCode {
    CART_ITEM_NOT_FOUND(404, CartItemErrorMessage.CART_ITEM_NOT_FOUND, HttpStatus.NOT_FOUND),
    CART_ITEM_QUANTITY_INVALID(422, CartItemErrorMessage.CART_ITEM_QUANTITY_INVALID, HttpStatus.UNPROCESSABLE_ENTITY),
    CART_ITEM_ALREADY_EXISTS(409, CartItemErrorMessage.CART_ITEM_ALREADY_EXISTS, HttpStatus.CONFLICT),
    CART_ITEM_QUANTITY_EXCEEDS_STOCK(
            422, CartItemErrorMessage.CART_ITEM_QUANTITY_EXCEEDS_STOCK, HttpStatus.UNPROCESSABLE_ENTITY),
    CART_ITEM_UPDATE_FAILED(500, CartItemErrorMessage.CART_ITEM_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    CART_ITEM_DELETE_FAILED(500, CartItemErrorMessage.CART_ITEM_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    CART_ITEM_NOT_BELONG_TO_USER(403, CartItemErrorMessage.CART_ITEM_NOT_BELONG_TO_USER, HttpStatus.FORBIDDEN),
    CART_ITEM_DISH_NOT_AVAILABLE(404, CartItemErrorMessage.CART_ITEM_DISH_NOT_AVAILABLE, HttpStatus.NOT_FOUND),
    CART_ITEM_RESTAURANT_INVALID(
            422, CartItemErrorMessage.CART_ITEM_RESTAURANT_INVALID, HttpStatus.UNPROCESSABLE_ENTITY);

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
