package com.natureblessings.orderService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemDTO {

    private Long productId;
    private Integer quantity;
}


