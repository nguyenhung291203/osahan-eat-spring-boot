package org.example.osahaneatspringboot.dto.response;

import java.math.BigDecimal;

import org.example.osahaneatspringboot.entity.Dish;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    String id;
    Long quantity;
    Dish dish;
    BigDecimal price;
}
