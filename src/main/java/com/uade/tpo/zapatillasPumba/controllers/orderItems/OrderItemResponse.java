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
    final Double subTotal = (unitPrice * quantity) ;
    final Double total = subTotal - (discountApplied * subTotal);
    
}

