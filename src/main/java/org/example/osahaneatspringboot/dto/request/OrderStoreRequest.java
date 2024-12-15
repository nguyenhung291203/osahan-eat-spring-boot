package org.example.osahaneatspringboot.dto.request;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStoreRequest {
    List<CartItemStoreRequest> items;
    String address;
    String phoneNumber;
    String fullName;
}
