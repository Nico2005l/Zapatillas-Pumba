package com.uade.tpo.zapatillasPumba.service.CartItem;

import com.uade.tpo.zapatillasPumba.entity.*;
import com.uade.tpo.zapatillasPumba.exceptions.CartItemNotFoundException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductOutOfStockException;
import com.uade.tpo.zapatillasPumba.repository.CartItemRepository;
import com.uade.tpo.zapatillasPumba.repository.DiscountRepository;
import com.uade.tpo.zapatillasPumba.service.Cart.CartService;
import com.uade.tpo.zapatillasPumba.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private DiscountRepository discountRepository;

    @Override
    @Transactional
    public CartItem addItemToCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartService.getCartById(cartId);
        Product product = productService.getProductById(productId);

        if (product.getStock() < quantity) {
            throw new ProductOutOfStockException("Not enough stock available");
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        // Apply discount if exists
        Optional<Discount> discount = discountRepository.findByProductId(product.getId())
            .stream()
            .findFirst();
        
        cartItem.setDiscountApplied(discount.map(Discount::getValue).orElse(0.0));

        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public CartItem updateCartItem(Long cartItemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id: " + cartItemId));

        if (cartItem.getProduct().getStock() < quantity) {
            throw new ProductOutOfStockException("Not enough stock available");
        }

        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id: " + cartItemId));
        
        cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public void clearCartItems(Long cartId) {
        cartItemRepository.deleteAllCartItemsByCartId(cartId);
    }

    @Override
    public Double getDiscountedPrice(CartItem item) {
        Double basePrice = item.getProduct().getPrice();
        Double discountValue = item.getDiscountApplied() != null ? item.getDiscountApplied() : 0.0;
        // Multiply by 100 to convert from decimal to percentage
        return basePrice * (1 - (discountValue * 100 / 100));
    }

    @Override
    public Double getSubtotal(CartItem item) {
        return getDiscountedPrice(item) * item.getQuantity();
    }

}