package org.example.osahaneatspringboot.dto.response;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ApiResponse<T> {
    int code = 200;
    String message;
    T result;

    public ApiResponse() {
        this.code = Integer.parseInt(String.valueOf(OK.value()));
        this.message = OK.getReasonPhrase();
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(String message, T result) {
        this.message = message;
        this.result = result;
    }

    public static <T> ApiResponse<T> ok(T body) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("OK");
        response.setResult(body);
        return response;
    }

    public static <T> ApiResponse<T> created(T body) {
        ApiResponse<T> response = ok(body);
        response.setMessage("Tạo tài nguyên thành công");
        return response;
    }

    public static ApiResponse<Void> deleted() {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Xóa tài nguyên thành công");
        return response;
    }

    public static ApiResponse<Void> deleted(String message) {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage(message);
        return response;
    }

    public static <T> ResponseEntity<ApiResponse<T>> okEntity(T body) {
        return ResponseEntity.ok(ok(body));
    }
}
