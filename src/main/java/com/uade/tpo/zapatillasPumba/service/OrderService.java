package com.uade.tpo.zapatillasPumba.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.zapatillasPumba.entity.Order;

public interface OrderService {

    Optional<Order> createOrder(Order order);
    Optional<Order> updateOrder(Long id, Order order);
    boolean deleteOrder(Long id);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
}
