package org.example.osahaneatspringboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {
    @Min(value = 0, message = "Số trang phải lớn hơn hoặc bằng 0")
    @NotNull(message = "Số trang là bắt buộc")
    Integer page = 1;

    @Min(value = 1, message = "Giới hạn phải lớn hơn hoặc bằng 1")
    @NotNull(message = "Giới hạn là bắt buộc")
    Integer limit = 10;

    @Size(max = 255, message = "Chuỗi tìm kiếm không được vượt quá 255 ký tự")
    String search = "";

    @NotNull(message = "Cờ isPage là bắt buộc")
    Boolean isPage = true;
}
