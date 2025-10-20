package com.uade.tpo.zapatillasPumba.service.Cart;

import com.uade.tpo.zapatillasPumba.entity.Cart;


import java.util.Optional;

public interface CartService {
    Cart getCartById(Long id);
    Cart getUserCart(Long userId);
    Cart createCart(Long userId);
    void deleteCart(Long id);
    Optional<Cart> findByUserId(Long userId);
}