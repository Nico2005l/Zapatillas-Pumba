package com.uade.tpo.zapatillasPumba.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.zapatillasPumba.entity.Order;

public interface OrderService {

    void createOrder(Order order);
    void updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
}
