package com.natureblessings.orderService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {

    private Long orderId;
    private List<OrderItemDTO> items;
    private Double totalPrice;
}

