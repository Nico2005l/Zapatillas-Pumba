package com.uade.tpo.zapatillasPumba.Product;

import com.uade.tpo.zapatillasPumba.Category.Category;
import com.uade.tpo.zapatillasPumba.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    // Relación con usuario vendedor HAY QUE CHEQUEAR SI ES NECESARIO SI SOMOS UN SOLO VENDEDOR
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
}
