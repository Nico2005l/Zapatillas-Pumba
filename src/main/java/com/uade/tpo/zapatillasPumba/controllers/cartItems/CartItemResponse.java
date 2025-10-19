package com.uade.tpo.zapatillasPumba.controllers.cartItems;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long id;
    private Long productId;
    private String productTitle;
    private Double unitPrice;
    private Double discountApplied;
    private Integer quantity;
    private Double subTotal;
    private Double total;
}