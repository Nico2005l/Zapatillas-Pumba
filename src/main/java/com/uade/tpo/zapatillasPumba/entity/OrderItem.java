package com.uade.tpo.zapatillasPumba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con la orden
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Relación con el producto
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "discount_applied")
    private Double discountApplied;

    @Column(name = "quantity")
    private Integer quantity;
}