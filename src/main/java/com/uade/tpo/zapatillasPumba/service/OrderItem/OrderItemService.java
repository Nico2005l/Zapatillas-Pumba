package com.uade.tpo.zapatillasPumba.service.OrderItem;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.OrderItem;


public interface OrderItemService {
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
    OrderItem createOrderItem(com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemRequest orderItemRequest);
}
