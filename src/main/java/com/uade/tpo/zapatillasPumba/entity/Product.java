package com.uade.tpo.zapatillasPumba.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Double price;

    @Column
    private Integer stock;

    @Column(name = "is_visible")
    private Boolean isVisible;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    // Relación con categoría (muchos productos pueden tener una categoría)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;  

    // Relación con usuario vendedor
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages;
}
