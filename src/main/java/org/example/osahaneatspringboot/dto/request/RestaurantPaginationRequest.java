package org.example.osahaneatspringboot.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPaginationRequest extends PaginationRequest {
    String sortBy;
    Double latitude;
    Double longitude;
}
