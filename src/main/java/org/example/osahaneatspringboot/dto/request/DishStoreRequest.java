package org.example.osahaneatspringboot.dto.request;

import static org.example.osahaneatspringboot.constant.message.CategoryErrorMessage.CATEGORY_ID_NOT_BLANK;
import static org.example.osahaneatspringboot.constant.message.DishErrorMessage.*;
import static org.example.osahaneatspringboot.constant.message.RestaurantErrorMessage.RESTAURANT_ID_NOT_BLANK;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishStoreRequest {
    @NotBlank(message = DISH_NAME_NOT_BLANK)
    String name;

    @Size(max = 255, message = DISH_DESCRIPTION_SIZE)
    String description;

    @Builder.Default
    Boolean isFreeShip = false;

    @NotNull(message = DISH_PRICE_NOT_NULL)
    @DecimalMin(value = "0.0", inclusive = false, message = DISH_PRICE_NEGATIVE)
    BigDecimal price;

    @NotBlank(message = CATEGORY_ID_NOT_BLANK)
    String categoryId;

    @NotBlank(message = RESTAURANT_ID_NOT_BLANK)
    String restaurantId;
}
