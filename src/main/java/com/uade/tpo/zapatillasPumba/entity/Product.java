package com.uade.tpo.zapatillasPumba.entity;

import java.time.LocalDate;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String descripcionCorta;

    @Column(columnDefinition = "TEXT")
    private String descripcionLarga;  // renamed from description

    @Column
    private Double price;

    @Column
    private Integer stock;

    @Column
    private Integer size ;

    @Column
    private String color;

    @Column
    private String genre;

    @Column(name = "is_visible")
    private Boolean isVisible;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference("category-products")
    private Category category;  // This should only reference type categories (subcategories)

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductImage> productImages;

}
