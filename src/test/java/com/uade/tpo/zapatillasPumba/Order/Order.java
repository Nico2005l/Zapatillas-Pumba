package com.uade.tpo.zapatillasPumba.Order;

import com.uade.tpo.zapatillasPumba.OrderItem.OrderItem;
import com.uade.tpo.zapatillasPumba.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    private LocalDateTime createdAt;

    // Relación con los ítems de la orden
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
