package org.example.osahaneatspringboot.constant.error;

import java.util.Map;

import org.example.osahaneatspringboot.constant.message.OrderErrorMessage;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum OrderErrorCode implements BaseErrorCode {
    ORDER_NOT_FOUND(404, OrderErrorMessage.ORDER_NOT_FOUND, HttpStatus.NOT_FOUND),
    ORDER_CREATION_FAILED(422, OrderErrorMessage.ORDER_CREATION_FAILED, HttpStatus.UNPROCESSABLE_ENTITY),
    ORDER_UPDATE_FAILED(422, OrderErrorMessage.ORDER_UPDATE_FAILED, HttpStatus.UNPROCESSABLE_ENTITY),
    ORDER_PAYMENT_FAILED(422, OrderErrorMessage.ORDER_PAYMENT_FAILED, HttpStatus.UNPROCESSABLE_ENTITY),
    ORDER_CANCEL_FAILED(422, OrderErrorMessage.ORDER_CANCEL_FAILED, HttpStatus.UNPROCESSABLE_ENTITY),
    ORDER_ITEM_QUANTITY_EXCEEDED(422, OrderErrorMessage.ORDER_ITEM_QUANTITY_EXCEEDED, HttpStatus.UNPROCESSABLE_ENTITY),
    ORDER_STATUS_INVALID(400, OrderErrorMessage.ORDER_STATUS_INVALID, HttpStatus.BAD_REQUEST),
    ORDER_ADDRESS_INVALID(400, OrderErrorMessage.ORDER_ADDRESS_INVALID, HttpStatus.BAD_REQUEST),
    ORDER_ALREADY_PROCESSED(409, OrderErrorMessage.ORDER_ALREADY_PROCESSED, HttpStatus.CONFLICT),
    ORDER_OPERATION_FAILED(500, OrderErrorMessage.ORDER_OPERATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

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
