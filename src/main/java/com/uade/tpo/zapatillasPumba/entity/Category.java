package com.uade.tpo.zapatillasPumba.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {

    public Category() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Relación con la categoría padre
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference("category-parent")
    private Category parent;

    // Relación con las subcategorías
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonManagedReference("category-parent")
    private List<Category> children = new ArrayList<>();

    // Relación uno a muchos con productos
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("category-products")
    private List<Product> products = new ArrayList<>();

}
