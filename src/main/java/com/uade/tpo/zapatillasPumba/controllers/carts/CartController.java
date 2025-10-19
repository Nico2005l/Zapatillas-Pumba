package com.uade.tpo.zapatillasPumba.controllers.carts;

import com.uade.tpo.zapatillasPumba.entity.Cart;
import com.uade.tpo.zapatillasPumba.entity.CartItem;
import com.uade.tpo.zapatillasPumba.service.Cart.CartService;
import com.uade.tpo.zapatillasPumba.controllers.cartItems.CartItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.stream.Collectors;
import java.util.Optional;
import com.uade.tpo.zapatillasPumba.repository.DiscountRepository;
import com.uade.tpo.zapatillasPumba.service.CartItem.CartItemService;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private DiscountRepository discountRepository;  // Add this

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok(toCartResponse(cart));
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest request) {
        Cart cart = cartService.createCart(request.getUserId());
        CartResponse response = toCartResponse(cart);
        return ResponseEntity.created(URI.create("/carts/" + cart.getId())).body(response);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<DeleteResponse> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok(new DeleteResponse("El carrito se ha borrado correctamente"));
    }

    private CartResponse toCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        
        CartResponse.UserInfo userInfo = new CartResponse.UserInfo();
        userInfo.setId(cart.getUser().getId());
        userInfo.setUsername(cart.getUser().getUsername());
        response.setUser(userInfo);
        
        response.setItems(cart.getCartItems().stream()
            .map(this::toCartItemResponse)
            .collect(Collectors.toList()));
        
        response.setCreatedAt(cart.getCreatedAt());
        response.setTotal(cart.getTotal());
        
        return response;
    }

    private CartItemResponse toCartItemResponse(CartItem item) {
        CartItemResponse response = new CartItemResponse();
        response.setId(item.getId());
        response.setProductId(item.getProduct().getId());
        response.setProductTitle(item.getProduct().getTitle());
        response.setUnitPrice(item.getProduct().getPrice());
        response.setDiscountApplied(item.getDiscountApplied());
        response.setQuantity(item.getQuantity());
        response.setSubTotal(cartItemService.getDiscountedPrice(item));
        response.setTotal(cartItemService.getSubtotal(item));
        return response;
    }
}