package com.uade.tpo.zapatillasPumba.controllers.orderItems;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private Long orderId;
    private Double unitPrice;
    private Double discountApplied;
    private Integer quantity;
}
