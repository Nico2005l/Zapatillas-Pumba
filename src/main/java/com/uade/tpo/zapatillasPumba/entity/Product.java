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
    private LocalDate createdAt;

    // Relación con categoría (muchos productos pueden tener una categoría)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;    // This will be the gender category

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Category subcategory; // This will be the type category

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductImage> productImages;

    public void setDiscount(Discount discount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDiscount'");
    }
}
