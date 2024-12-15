package org.example.osahaneatspringboot.service.order;

import java.util.List;

import org.example.osahaneatspringboot.dto.request.OrderStoreRequest;
import org.example.osahaneatspringboot.entity.Order;

public interface IOrderService {
    Order store(String username, OrderStoreRequest request);

    List<Order> getOrdersByUsername(String username);

    Order getOrderDetail(String id);
}
