package com.uade.tpo.zapatillasPumba.service.Cart;

import com.uade.tpo.zapatillasPumba.entity.Cart;
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.exceptions.CartNotFoundException;
import com.uade.tpo.zapatillasPumba.repository.CartRepository;
import com.uade.tpo.zapatillasPumba.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private UserService userService;

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
            .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + id));
    }

    @Override
    public Cart getUserCart(Long userId) {
        return cartRepository.findByUserId(userId)
            .orElseGet(() -> createCart(userId));
    }

    @Override
    @Transactional
    public Cart createCart(Long userId) {
        User user = userService.getUserById(userId);
        
        // Check if user already has a cart
        cartRepository.findByUserId(userId).ifPresent(cart -> {
            throw new IllegalStateException("User already has an active cart");
        });

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCreatedAt(LocalDateTime.now());
        
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void deleteCart(Long id) {
        Cart cart = getCartById(id);
        cartRepository.delete(cart);
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}