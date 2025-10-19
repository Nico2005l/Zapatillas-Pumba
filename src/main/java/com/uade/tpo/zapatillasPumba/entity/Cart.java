package com.uade.tpo.zapatillasPumba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();

    private LocalDateTime createdAt;

    @JsonIgnore
    public Double getTotal() {
        return cartItems.stream()
            .mapToDouble(item -> {
                Double basePrice = item.getProduct().getPrice();
                Double discount = item.getDiscountApplied() != null ? item.getDiscountApplied() : 0.0;
                return (basePrice * (1 - (discount / 100))) * item.getQuantity();
            })
            .sum();
    }

    @JsonIgnore
    public Double getTotalSavings() {
        return cartItems.stream()
            .mapToDouble(item -> {
                Double basePrice = item.getProduct().getPrice() * item.getQuantity();
                Double discountedPrice = (basePrice * (1 - (item.getDiscountApplied() != null ? 
                    item.getDiscountApplied() / 100 : 0)));
                return basePrice - discountedPrice;
            })
            .sum();
    }
}