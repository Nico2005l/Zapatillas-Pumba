package com.uade.tpo.zapatillasPumba.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uade.tpo.zapatillasPumba.serializer.CategorySerializer;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonSerialize(using = CategorySerializer.class)
public class Category {

    public Category() {
    }

    public Category(SexoCategory sexo, TipoCategory tipo) {
        this.sexo = sexo;
        this.tipo = tipo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private SexoCategory sexo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoCategory tipo;

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
    @OneToMany(mappedBy = "category")
    @JsonManagedReference("category-products")
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    public boolean isGenderCategory() {
        return sexo != null;
    }

    @JsonIgnore
    public boolean isTypeCategory() {
        return tipo != null;
    }
}
