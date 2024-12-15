package org.example.osahaneatspringboot.mapper;

import org.example.osahaneatspringboot.dto.response.OrderDetailResponse;
import org.example.osahaneatspringboot.dto.response.OrderResponse;
import org.example.osahaneatspringboot.entity.CartItem;
import org.example.osahaneatspringboot.entity.Order;
import org.example.osahaneatspringboot.entity.OrderDetail;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDetail toOrderDetail(CartItem request) {
        return OrderDetail.builder()
                .dish(request.getDish())
                .quantity(request.getQuantity())
                .build();
    }

    public OrderDetailResponse toOrderDetailResponse(OrderDetail request) {
        return OrderDetailResponse.builder()
                .id(request.getId())
                .dish(request.getDish())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }

    public OrderResponse toOrderResponse(Order request) {
        return OrderResponse.builder()
                .id(request.getId())
                .restaurant(request.getRestaurant())
                .price(request.getPrice())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .status(request.getStatus())
                .fullName(request.getFullName())
                .orderDetails(request.getOrderDetails().stream()
                        .map(this::toOrderDetailResponse)
                        .toList())
                .createAt(request.getCreateAt())
                .updateAt(request.getUpdateAt())
                .build();
    }
}
