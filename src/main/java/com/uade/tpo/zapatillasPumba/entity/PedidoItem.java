package com.uade.tpo.zapatillasPumba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pedido_items")
public class PedidoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;

    private Double unitPrice;

    private Double discountApplied;

    private Double subtotal;
}