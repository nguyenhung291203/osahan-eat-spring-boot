package org.example.osahaneatspringboot.service.cart;

import java.util.List;

import org.example.osahaneatspringboot.dto.request.CartItemStoreRequest;
import org.example.osahaneatspringboot.dto.request.CartItemUpdateRequest;
import org.example.osahaneatspringboot.entity.CartItem;

public interface ICartService {
    List<CartItem> getCartMe(String username);

    List<CartItem> getCartItem(List<String> ids);

    CartItem store(String username, CartItemStoreRequest request);

    CartItem update(String id, String username, CartItemUpdateRequest request);

    Void remove(String id, String username);

    Void removeAll(List<String> ids, String username);

    void removeDishIds(List<String> ids, String username);
}
