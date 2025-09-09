package com.uade.tpo.zapatillasPumba.service.OrderItem;

import java.util.List;

import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemRequest;
import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemResponse;
import com.uade.tpo.zapatillasPumba.entity.OrderItem;


public interface OrderItemService {
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
    OrderItem createOrderItem(OrderItemRequest orderItemRequest);
    
    // New methods for returning DTOs directly
    List<OrderItemResponse> getOrderItemResponsesByOrderId(Long orderId);
    OrderItemResponse createOrderItemResponse(OrderItemRequest orderItemRequest);
}
