package com.uade.tpo.zapatillasPumba.controllers.carts;

import com.uade.tpo.zapatillasPumba.controllers.cartItems.CartItemResponse;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartResponse {
    private Long id;
    private UserInfo user;
    private List<CartItemResponse> items;
    private LocalDateTime createdAt;
    private Double total;

    @Data
    public static class UserInfo {
        private Long id;
        private String username;
    }
}