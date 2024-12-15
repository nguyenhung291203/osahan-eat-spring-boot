package org.example.osahaneatspringboot.service.order;

import static org.example.osahaneatspringboot.constant.cache.CacheKey.ORDER_KEY;
import static org.example.osahaneatspringboot.constant.code.OrderStatus.PENDING;
import static org.example.osahaneatspringboot.constant.error.AccountErrorCode.ACCOUNT_NOT_EXISTED;
import static org.example.osahaneatspringboot.constant.error.DishErrorCode.DISH_NOT_FOUND;
import static org.example.osahaneatspringboot.constant.error.OrderErrorCode.ORDER_NOT_FOUND;

import java.math.BigDecimal;
import java.util.List;

import jakarta.transaction.Transactional;

import org.example.osahaneatspringboot.dto.request.CartItemStoreRequest;
import org.example.osahaneatspringboot.dto.request.OrderStoreRequest;
import org.example.osahaneatspringboot.entity.*;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.repository.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    IOrderRepository orderRepository;
    IOrderDetailRepository orderDetailRepository;
    IDishRepository dishRepository;
    ICartRepository cartRepository;
    IAccountRepository accountRepository;

    private Account getAccount(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new ApiException(ACCOUNT_NOT_EXISTED));
    }

    @Override
    @Transactional
    @CacheEvict(value = ORDER_KEY, key = "#username")
    public Order store(String username, OrderStoreRequest request) {
        Account account = getAccount(username);
        List<Dish> dishes = dishRepository.findAllById(
                request.getItems().stream().map(CartItemStoreRequest::getDishId).toList());
        if (dishes.isEmpty()) {
            throw new ApiException(DISH_NOT_FOUND);
        }
        Restaurant restaurant = dishes.getFirst().getRestaurant();
        BigDecimal totalPrice = request.getItems().stream()
                .map(item -> {
                    Dish dish = dishes.stream()
                            .filter(d -> d.getId().equals(item.getDishId()))
                            .findFirst()
                            .orElseThrow(() -> new ApiException(DISH_NOT_FOUND));
                    return dish.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .account(account)
                .restaurant(restaurant)
                .price(totalPrice)
                .status(PENDING.name())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .fullName(request.getFullName())
                .build();

        Order savedOrder = orderRepository.save(order);
        orderRepository.flush();

        List<OrderDetail> orderDetails = request.getItems().stream()
                .map(item -> {
                    Dish dish = dishes.stream()
                            .filter(d -> d.getId().equals(item.getDishId()))
                            .findFirst()
                            .orElseThrow(() -> new ApiException(DISH_NOT_FOUND));
                    return OrderDetail.builder()
                            .order(savedOrder)
                            .dish(dish)
                            .quantity(item.getQuantity())
                            .price(dish.getPrice())
                            .build();
                })
                .toList();

        orderDetailRepository.saveAll(orderDetails);
        cartRepository.removeDishIds(
                request.getItems().stream().map(CartItemStoreRequest::getDishId).toList(), username);

        savedOrder.setOrderDetails(orderDetails);

        return savedOrder;
    }

    @Override
    @Cacheable(value = ORDER_KEY, key = "#username")
    public List<Order> getOrdersByUsername(String username) {
        Account account = getAccount(username);
        return orderRepository.findByAccountUsername(account.getUsername());
    }

    @Override
    @Cacheable(value = ORDER_KEY, key = "#id")
    public Order getOrderDetail(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new ApiException(ORDER_NOT_FOUND));
    }
}
