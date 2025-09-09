package com.uade.tpo.zapatillasPumba.controllers.orderItems;


import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private Long orderId;
    private Long productId;
    private Double unitPrice;
    private Double discountApplied;
    private Integer quantity;

    private Double subTotal;
    private Double total;
}

