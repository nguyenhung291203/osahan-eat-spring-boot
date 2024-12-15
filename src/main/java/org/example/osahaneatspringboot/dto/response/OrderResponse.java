package org.example.osahaneatspringboot.dto.response;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.example.osahaneatspringboot.entity.Restaurant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    Restaurant restaurant;
    BigDecimal price;
    String address;
    String phoneNumber;
    String status;
    String fullName;
    List<OrderDetailResponse> orderDetails;

    @JsonFormat(pattern = DATE_FORMAT)
    private Date createAt;

    @JsonFormat(pattern = DATE_FORMAT)
    private Date updateAt;
}
