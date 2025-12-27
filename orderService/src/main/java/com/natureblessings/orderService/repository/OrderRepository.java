package com.natureblessings.orderService.repository;

import com.natureblessings.orderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
