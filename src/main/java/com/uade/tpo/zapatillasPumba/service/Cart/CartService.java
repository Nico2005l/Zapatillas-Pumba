package com.uade.tpo.zapatillasPumba.service.Cart;

import com.uade.tpo.zapatillasPumba.entity.Cart;
import java.util.List;

public interface CartService {
    Cart getCartById(Long id);
    Cart getUserCart(Long userId);
    Cart createCart(Long userId);
    void deleteCart(Long id);
}