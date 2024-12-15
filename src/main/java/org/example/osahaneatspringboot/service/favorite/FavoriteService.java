package org.example.osahaneatspringboot.service.favorite;

import static org.example.osahaneatspringboot.constant.error.AccountErrorCode.ACCOUNT_NOT_EXISTED;
import static org.example.osahaneatspringboot.constant.error.RestaurantErrorCode.RESTAURANT_NOT_FOUND;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import jakarta.transaction.Transactional;

import org.example.osahaneatspringboot.constant.cache.CacheKey;
import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.entity.FavoriteRestaurant;
import org.example.osahaneatspringboot.entity.Restaurant;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.repository.IAccountRepository;
import org.example.osahaneatspringboot.repository.IFavoriteRestaurantRepository;
import org.example.osahaneatspringboot.repository.IRestaurantRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FavoriteService implements IFavoriteService {
    IFavoriteRestaurantRepository favoriteRestaurantRepository;
    IAccountRepository accountRepository;
    IRestaurantRepository restaurantRepository;

    Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new ApiException(ACCOUNT_NOT_EXISTED));
    }

    Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new ApiException(RESTAURANT_NOT_FOUND));
    }

    @Override
    @Transactional
    @CacheEvict(value = CacheKey.FAVORITE_RESTAURANT_KEY, key = "#username")
    public Restaurant store(String username, String restaurantId) throws ExecutionException, InterruptedException {
        CompletableFuture<Account> accountFuture = CompletableFuture.supplyAsync(() -> getAccountByUsername(username));
        CompletableFuture<Restaurant> restaurantFuture =
                CompletableFuture.supplyAsync(() -> getRestaurantById(restaurantId));
        CompletableFuture.allOf(accountFuture, restaurantFuture).join();

        Account account = accountFuture.join();
        Restaurant restaurant = restaurantFuture.join();
        FavoriteRestaurant favorite = favoriteRestaurantRepository
                .findByAccountIdAndRestaurantId(account.getId(), restaurant.getId())
                .orElse(null);
        if (favorite != null) {
            return restaurant;
        }
        favorite = FavoriteRestaurant.builder()
                .account(account)
                .restaurant(restaurant)
                .build();
        favoriteRestaurantRepository.saveAndFlush(favorite);
        return restaurant;
    }

    @Override
    @Transactional
    @CacheEvict(value = CacheKey.FAVORITE_RESTAURANT_KEY, key = "#username")
    public void delete(String username, String restaurantId) {
        CompletableFuture<Account> accountFuture = CompletableFuture.supplyAsync(() -> getAccountByUsername(username));

        CompletableFuture<Restaurant> restaurantFuture =
                CompletableFuture.supplyAsync(() -> getRestaurantById(restaurantId));
        CompletableFuture.allOf(accountFuture, restaurantFuture).join();
        Account account = accountFuture.join();
        Restaurant restaurant = restaurantFuture.join();
        FavoriteRestaurant favorite = favoriteRestaurantRepository
                .findByAccountIdAndRestaurantId(account.getId(), restaurant.getId())
                .orElse(null);
        if (favorite == null) {
            return;
        }
        favoriteRestaurantRepository.delete(favorite);
    }

    @Override
    @Cacheable(value = CacheKey.FAVORITE_RESTAURANT_KEY, key = "#username")
    public List<Restaurant> findRestaurantFavorite(String username) {
        Account account = getAccountByUsername(username);

        return favoriteRestaurantRepository.findAllByAccountId(account.getId());
    }
}
