package org.example.osahaneatspringboot.mapper;

import org.example.osahaneatspringboot.dto.response.CartItemResponse;
import org.example.osahaneatspringboot.entity.CartItem;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartMapper {
    DishMapper dishMapper;

    public CartItemResponse toCartItemResponse(CartItem request) {
        return CartItemResponse.builder()
                .id(request.getId())
                .quantity(request.getQuantity())
                .dish(dishMapper.toDishResponse(request.getDish()))
                .build();
    }
}
