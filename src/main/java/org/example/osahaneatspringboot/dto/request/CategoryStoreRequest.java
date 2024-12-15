package org.example.osahaneatspringboot.dto.request;

import static org.example.osahaneatspringboot.constant.message.CategoryErrorMessage.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CategoryStoreRequest {
    @NotBlank(message = CATEGORY_NAME_NOT_BLANK)
    @Size(min = 1, max = 255, message = CATEGORY_NAME_SIZE)
    private String name;

    @Size(max = 255, message = CATEGORY_DESCRIPTION_SIZE)
    private String description;
}
