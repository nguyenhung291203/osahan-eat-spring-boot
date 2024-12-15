package org.example.osahaneatspringboot.service.cart;

import static org.example.osahaneatspringboot.constant.error.AccountErrorCode.ACCOUNT_NOT_EXISTED;
import static org.example.osahaneatspringboot.constant.error.CartItemErrorCode.CART_ITEM_NOT_FOUND;
import static org.example.osahaneatspringboot.constant.error.DishErrorCode.DISH_NOT_FOUND;

import java.util.List;

import jakarta.transaction.Transactional;

import org.example.osahaneatspringboot.constant.cache.CacheKey;
import org.example.osahaneatspringboot.dto.request.CartItemStoreRequest;
import org.example.osahaneatspringboot.dto.request.CartItemUpdateRequest;
import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.entity.CartItem;
import org.example.osahaneatspringboot.entity.Dish;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.repository.IAccountRepository;
import org.example.osahaneatspringboot.repository.ICartRepository;
import org.example.osahaneatspringboot.repository.IDishRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartService implements ICartService {
    ICartRepository repository;
    IAccountRepository accountRepository;
    IDishRepository dishRepository;

    private Account getAccount(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new ApiException(ACCOUNT_NOT_EXISTED));
    }

    private Dish getDish(String id) {
        return dishRepository.findById(id).orElseThrow(() -> new ApiException(DISH_NOT_FOUND));
    }

    @Override
    @Cacheable(value = CacheKey.CART_KEY, key = "#username")
    public List<CartItem> getCartMe(String username) {
        Account account = getAccount(username);
        return repository.findAllByAccountId(account.getId());
    }

    @Override
    @Cacheable(value = CacheKey.CART_KEY, key = "#ids")
    public List<CartItem> getCartItem(List<String> ids) {
        return repository.findAllById(ids);
    }

    @Override
    @CacheEvict(value = CacheKey.CART_KEY, key = "#username")
    public CartItem store(String username, CartItemStoreRequest request) {
        Dish dish = getDish(request.getDishId());

        Account account = getAccount(username);
        CartItem cartItem = repository
                .findByDishIdAndAccountId(dish.getId(), account.getId())
                .orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            return repository.save(cartItem);
        }
        cartItem = CartItem.builder()
                .account(account)
                .dish(dish)
                .quantity(request.getQuantity())
                .build();
        return repository.save(cartItem);
    }

    @Override
    @CacheEvict(value = CacheKey.CART_KEY, key = "#username")
    public CartItem update(String id, String username, CartItemUpdateRequest request) {
        CartItem cartItem = repository.findById(id).orElseThrow(() -> new ApiException(CART_ITEM_NOT_FOUND));

        cartItem.setQuantity(request.getQuantity());
        return repository.save(cartItem);
    }

    @Override
    @CacheEvict(value = CacheKey.CART_KEY, key = "#username")
    public Void remove(String id, String username) {
        if (!repository.existsById(id)) {
            throw new ApiException(CART_ITEM_NOT_FOUND);
        }
        repository.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(value = CacheKey.CART_KEY, key = "#username")
    public Void removeAll(List<String> ids, String username) {
        if (!repository.existsByIdIn(ids)) {
            throw new ApiException(CART_ITEM_NOT_FOUND);
        }
        repository.deleteAllById(ids);
        return null;
    }

    @Override
    @CacheEvict(value = CacheKey.CART_KEY, key = "#username")
    public void removeDishIds(List<String> ids, String username) {
        List<CartItem> items = repository.findByDishIdsAndUsername(ids, username);
        repository.deleteAll(items);
    }
}
