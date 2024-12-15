package org.example.osahaneatspringboot.dto.request;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.example.osahaneatspringboot.annotation.Trimmed;
import org.example.osahaneatspringboot.annotation.ValidOpenCloseTime;
import org.example.osahaneatspringboot.constant.message.RestaurantErrorMessage;
import org.example.osahaneatspringboot.constant.regexp.RegexPatterns;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Trimmed
@ValidOpenCloseTime
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantCreationRequest {
    @Pattern(
            regexp = RegexPatterns.ALPHANUMERIC_WITH_SPACES_AND_VIETNAMESE_ACCENTS_PATTERN,
            message = RestaurantErrorMessage.TITLE_INVALID_CHARACTERS)
    @NotBlank(message = RestaurantErrorMessage.TITLE_REQUIRED)
    @Size(min = 3, max = 50, message = RestaurantErrorMessage.TITLE_LENGTH_INVALID)
    String title;

    @Size(max = 255, message = RestaurantErrorMessage.SUBTITLE_TOO_LONG)
    String subtitle;

    @NotBlank(message = RestaurantErrorMessage.ADDRESS_REQUIRED)
    @Size(min = 10, max = 255, message = RestaurantErrorMessage.ADDRESS_LENGTH_INVALID)
    String address;

    @Size(max = 255, message = RestaurantErrorMessage.DESCRIPTION_TOO_LONG)
    String description;

    Boolean isFreeShip;
    Boolean isActive;

    @NotNull(message = RestaurantErrorMessage.OPEN_TIME_REQUIRED)
    LocalTime openTime;

    @NotNull(message = RestaurantErrorMessage.CLOSE_TIME_REQUIRED)
    LocalTime closeTime;

    @NotNull(message = RestaurantErrorMessage.LATITUDE_REQUIRED)
    Double latitude;

    @NotNull(message = RestaurantErrorMessage.LONGITUDE_REQUIRED)
    Double longitude;
}
