package com.uade.tpo.zapatillasPumba.controllers.cartItems;

import com.uade.tpo.zapatillasPumba.entity.CartItem;
import com.uade.tpo.zapatillasPumba.service.CartItem.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carts/{cartId}/items")
public class CartItemsController {
    
    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartItemService.getCartItems(cartId));
    }

    @PostMapping
    public ResponseEntity<CartItem> addCartItem(
            @PathVariable Long cartId,
            @RequestBody CartItemRequest request) {
        CartItem cartItem = cartItemService.addItemToCart(
            cartId, 
            request.getProductId(), 
            request.getQuantity()
        );
        return ResponseEntity.created(
            URI.create("/carts/" + cartId + "/items/" + cartItem.getId())
        ).body(cartItem);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable Long cartId,
            @PathVariable Long itemId,
            @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(
            cartItemService.updateCartItem(itemId, request.getQuantity())
        );
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeCartItem(
            @PathVariable Long cartId,
            @PathVariable Long itemId) {
        cartItemService.removeCartItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<DeleteResponse> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.ok(new DeleteResponse("El item del carrito se ha borrado correctamente"));
    }
}