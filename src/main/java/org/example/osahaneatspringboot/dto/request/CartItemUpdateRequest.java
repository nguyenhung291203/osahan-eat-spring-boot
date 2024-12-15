package org.example.osahaneatspringboot.dto.request;

import static org.example.osahaneatspringboot.constant.message.CartErrorMessage.CART_ITEM_QUANTITY_TOO_LOW;

import jakarta.validation.constraints.Min;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemUpdateRequest {
    @Min(value = 1, message = CART_ITEM_QUANTITY_TOO_LOW)
    long quantity;
}
