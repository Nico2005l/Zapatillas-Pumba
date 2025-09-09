package com.uade.tpo.zapatillasPumba.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con usuario
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private Double total;

    @Column
    private String status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    // Relación con los ítems de la orden
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
