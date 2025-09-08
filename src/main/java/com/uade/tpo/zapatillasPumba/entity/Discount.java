package com.uade.tpo.zapatillasPumba.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con producto
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Double value;

    @Column(name = "start_at")
    private LocalDate startAt;

    @Column(name = "end_at")
    private LocalDate endAt;

    @Column(name = "is_active")
    private Boolean isActive;
}
