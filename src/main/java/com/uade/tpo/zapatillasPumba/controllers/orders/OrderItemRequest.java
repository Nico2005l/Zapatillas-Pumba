package com.uade.tpo.zapatillasPumba.controllers.orders;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private Long sellerId;
    private Double unitPrice;
    private Double discountApplied;
    private Integer quantity;
}
