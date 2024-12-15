package org.example.osahaneatspringboot.repository;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.example.osahaneatspringboot.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findAllByAccountId(String accountId);

    Optional<CartItem> findByDishIdAndAccountId(String dishId, String accountId);

    boolean existsByIdIn(List<String> ids);

    @Query("SELECT c FROM CartItem c WHERE c.dish.id IN :ids AND c.account.username = :username")
    List<CartItem> findByDishIdsAndUsername(@Param("ids") List<String> ids, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.dish.id IN :ids AND c.account.username = :username")
    void removeDishIds(@Param("ids") List<String> ids, @Param("username") String username);
}
