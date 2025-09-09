package com.uade.tpo.zapatillasPumba.controllers.orders;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemResponse;

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
