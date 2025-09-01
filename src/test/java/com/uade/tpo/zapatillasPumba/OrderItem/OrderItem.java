package com.uade.tpo.zapatillasPumba.OrderItem;

import com.uade.tpo.zapatillasPumba.Order.Order;
import com.uade.tpo.zapatillasPumba.Product.Product;
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
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con la orden
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Relación con el producto
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Relación con el vendedor
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "discount_applied")
    private Double discountApplied;

    @Column(name = "quantity")
    private Integer quantity;
}