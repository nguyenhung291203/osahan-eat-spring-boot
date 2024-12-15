package org.example.osahaneatspringboot.repository;

import java.util.List;

import org.example.osahaneatspringboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, String> {
    List<Order> findByAccountUsername(String username);
}
