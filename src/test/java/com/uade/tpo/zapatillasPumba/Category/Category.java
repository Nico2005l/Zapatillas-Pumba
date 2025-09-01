package com.uade.tpo.zapatillasPumba.Category;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import com.uade.tpo.zapatillasPumba.Product.Product;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

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

