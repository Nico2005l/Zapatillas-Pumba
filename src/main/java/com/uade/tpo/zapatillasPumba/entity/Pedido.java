package com.uade.tpo.zapatillasPumba.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pedidoNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> items = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PedidoStatus status;

    private Double totalAmount;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = PedidoStatus.EN_PROCESO;
        pedidoNumber = generatePedidoNumber();
    }

    private String generatePedidoNumber() {
        // Format: P + 4 digits (P1001, P1002, etc.)
        return "P" + String.format("%04d", (int) (Math.random() * 9000 + 1000));
    }
}