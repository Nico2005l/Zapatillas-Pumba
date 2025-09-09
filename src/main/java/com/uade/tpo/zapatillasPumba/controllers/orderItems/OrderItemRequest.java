package com.uade.tpo.zapatillasPumba.controllers.orderItems;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private Long sellerId;
    private Long orderId;
    private Double unitPrice;
    private Integer quantity;
}
