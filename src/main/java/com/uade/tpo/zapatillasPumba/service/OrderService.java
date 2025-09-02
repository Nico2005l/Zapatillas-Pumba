package com.uade.tpo.zapatillasPumba.service;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.Order;

public interface OrderService {

    void createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    void updateOrder(Long id, Order order);
    void deleteOrder(Long id);
}
