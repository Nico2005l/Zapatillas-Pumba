package com.uade.tpo.zapatillasPumba.controllers.orders;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.Order;

public class OrderResponse {
    private List<Order> orders;

    public OrderResponse(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
