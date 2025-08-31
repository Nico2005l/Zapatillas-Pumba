package com.uade.tpo.zapatillasPumba.entity;

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
    private Category parent;

    // Relación con las subcategorías
    @OneToMany(mappedBy = "parent")
    private List<Category> subcategories;

    // Relación uno a muchos con productos
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
