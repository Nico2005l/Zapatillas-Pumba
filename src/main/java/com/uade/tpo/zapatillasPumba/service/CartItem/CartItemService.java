package com.uade.tpo.zapatillasPumba.service.CartItem;

import com.uade.tpo.zapatillasPumba.entity.CartItem;
import java.util.List;

public interface CartItemService {
    CartItem addItemToCart(Long cartId, Long productId, Integer quantity);
    CartItem updateCartItem(Long cartItemId, Integer quantity);
    void removeCartItem(Long cartItemId);
    List<CartItem> getCartItems(Long cartId);
    Double getDiscountedPrice(CartItem item);
    Double getSubtotal(CartItem item);
    void clearCartItems(Long cartId);
}