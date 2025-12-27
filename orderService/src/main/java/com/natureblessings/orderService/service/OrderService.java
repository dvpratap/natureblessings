package com.natureblessings.orderService.service;

import com.natureblessings.orderService.dto.OrderDTO;
import com.natureblessings.orderService.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDTO saveOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    void deleteOrder(Long id);
}
