package com.uade.tpo.zapatillasPumba.service;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.Order;

public interface OrderItemService {
    void createOrderItem(Order order);
    Order getOrderItemById(Long id);
    List<Order> getAllOrdersItems();
    void updateOrderItem(Long id, Order order);
    void deleteOrderItem(Long id);
}
