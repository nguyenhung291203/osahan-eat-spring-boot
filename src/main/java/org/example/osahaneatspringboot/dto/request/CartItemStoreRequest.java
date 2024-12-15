package org.example.osahaneatspringboot.dto.request;

import static org.example.osahaneatspringboot.constant.message.CartErrorMessage.CART_ITEM_QUANTITY_TOO_LOW;
import static org.example.osahaneatspringboot.constant.message.DishErrorMessage.DISH_ID_NOT_BLANK;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemStoreRequest {
    @NotBlank(message = DISH_ID_NOT_BLANK)
    String dishId;

    @Builder.Default
    @Min(value = 1, message = CART_ITEM_QUANTITY_TOO_LOW)
    long quantity = 1;
}
