package org.example.osahaneatspringboot.repository;

import org.example.osahaneatspringboot.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, String> {}
