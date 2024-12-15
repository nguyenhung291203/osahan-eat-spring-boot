package org.example.osahaneatspringboot.dto.request;

import static org.example.osahaneatspringboot.constant.message.RestaurantErrorMessage.*;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RestaurantStoreRequest {
    @NotBlank(message = RESTAURANT_ID_NOT_BLANK)
    @Size(min = 3, max = 50, message = TITLE_LENGTH_INVALID)
    String title;

    @Size(max = 255, message = SUBTITLE_TOO_LONG)
    String subtitle;

    @NotBlank(message = ADDRESS_REQUIRED)
    @Size(min = 10, max = 255, message = ADDRESS_LENGTH_INVALID)
    String address;

    @Size(max = 255, message = DESCRIPTION_TOO_LONG)
    String description;

    Boolean isFreeShip;
    Boolean isActive;

    @NotNull(message = OPEN_TIME_REQUIRED)
    LocalTime openTime;

    @NotNull(message = CLOSE_TIME_REQUIRED)
    LocalTime closeTime;

    @NotNull(message = LATITUDE_REQUIRED)
    Double latitude;

    @NotNull(message = LONGITUDE_REQUIRED)
    Double longitude;
}
