package org.example.osahaneatspringboot.constant.error;

import java.util.Map;

import org.example.osahaneatspringboot.constant.message.AccountErrorMessage;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum AccountErrorCode implements BaseErrorCode {
    ACCOUNT_EXISTED(422, AccountErrorMessage.ACCOUNT_EXISTED, HttpStatus.CONFLICT),
    ACCOUNT_NOT_EXISTED(404, AccountErrorMessage.ACCOUNT_NOT_EXISTED, HttpStatus.NOT_FOUND),
    USERNAME_INVALID(422, AccountErrorMessage.USERNAME_INVALID, HttpStatus.CONFLICT),
    USERNAME_EMPTY(422, AccountErrorMessage.USERNAME_EMPTY, HttpStatus.CONFLICT),
    PASSWORD_EMPTY(422, AccountErrorMessage.PASSWORD_EMPTY, HttpStatus.CONFLICT),
    INCORRECT_PASSWORD(422, AccountErrorMessage.INCORRECT_PASSWORD, HttpStatus.CONFLICT),
    ACCOUNT_LOCKED(401, AccountErrorMessage.ACCOUNT_LOCKED, HttpStatus.UNAUTHORIZED);

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
