package com.uade.tpo.zapatillasPumba.controllers.orders;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemResponse;
import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.entity.OrderItem;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private Long userId;
    private Double total;
    private String status;
    private LocalDate createdAt;
    private List<OrderItemResponse> items;

    // Constructor
    public OrderResponse() {

    }
}
