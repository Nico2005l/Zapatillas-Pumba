package com.uade.tpo.zapatillasPumba.controllers.carts;

import com.uade.tpo.zapatillasPumba.controllers.cartItems.CartItemResponse;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartResponse {
    private Long id;
    private Long userId;
    private List<CartItemResponse> items;
    private LocalDateTime createdAt;
    private Double total;

    
}