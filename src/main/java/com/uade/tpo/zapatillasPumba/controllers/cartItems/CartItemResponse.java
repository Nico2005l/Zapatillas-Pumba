package com.uade.tpo.zapatillasPumba.controllers.cartItems;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long id;
    private Long productId;
    private String productTitle;
    private Integer productSize;
    private String productColor;
    private String productGenre;
    private Double unitPrice;
    private Double discountApplied;
    private Integer quantity;
    private Double subTotal;
    private Double total;
}