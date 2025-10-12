package com.uade.tpo.zapatillasPumba.service.Order;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.zapatillasPumba.controllers.orders.OrderRequest;
import com.uade.tpo.zapatillasPumba.controllers.orders.OrderResponse;
import com.uade.tpo.zapatillasPumba.entity.Order;

public interface OrderService {

    // Entity methods
    Optional<Order> createOrder(OrderRequest order);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    List<Order> getOrderByUserId(Long userId);
    String deleteOrder(Long id);
    
    // DTO methods
    Optional<OrderResponse> createOrderResponse(OrderRequest order);
    List<OrderResponse> getAllOrderResponses();
    Optional<OrderResponse> getOrderResponseById(Long id);
    List<OrderResponse> getOrderResponsesByUserId(Long userId);
}
