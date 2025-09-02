package com.uade.tpo.zapatillasPumba.service.OrderItem;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.OrderItem;


public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(Long id);
    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem updateOrderItem(Long id, OrderItem orderItem);
    void deleteOrderItem(Long id);
}
