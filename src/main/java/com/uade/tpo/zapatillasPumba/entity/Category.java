package com.uade.tpo.zapatillasPumba.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Category {

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // Relación con la categoría padre
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference("category-parent")  // Detiene la recursión al serializar
    private Category parent;

    // Relación con las subcategorías
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonManagedReference("category-parent")  // Mantiene la serialización en esta dirección
    private List<Category> children;

    // Relación uno a muchos con productos
    @OneToMany(mappedBy = "category")
    @JsonManagedReference("category-products")  // Para relación con Product
    private List<Product> products;
}
