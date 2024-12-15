package org.example.osahaneatspringboot.dto.response;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantResponse {
    String id;
    String title;
    String subtitle;
    String address;
    String description;
    String image;
    Boolean isFreeShip;
    Boolean isActive;
    LocalTime openTime;
    LocalTime closeTime;
    Double latitude;
    Double longitude;

    @JsonFormat(pattern = DATE_FORMAT)
    private Date createAt;

    @JsonFormat(pattern = DATE_FORMAT)
    private Date updateAt;
}
