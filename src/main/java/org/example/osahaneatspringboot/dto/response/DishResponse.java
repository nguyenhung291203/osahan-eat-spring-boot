package org.example.osahaneatspringboot.dto.response;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

import java.math.BigDecimal;
import java.util.Date;

import org.example.osahaneatspringboot.entity.Category;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishResponse {
    String id;
    String name;
    String description;
    Boolean isFreeShip;
    BigDecimal timeShip;
    BigDecimal price;
    String image;
    Category category;
    RestaurantResponse restaurant;

    @JsonFormat(pattern = DATE_FORMAT)
    private Date createAt;

    @JsonFormat(pattern = DATE_FORMAT)
    private Date updateAt;
}
