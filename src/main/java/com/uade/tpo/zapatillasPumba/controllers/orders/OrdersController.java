package com.uade.tpo.zapatillasPumba.controllers.orders;


import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemResponse;
import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import com.uade.tpo.zapatillasPumba.service.Order.OrderService;

import io.jsonwebtoken.security.Jwks.OP;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderResponse> orderResponses = orders.stream().map(order -> {
            OrderResponse response = new OrderResponse();
            List<OrderItem> items = order.getItems();
            List<OrderItemResponse> itemResponses = items.stream().map(item -> {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setId(item.getId());
                itemResponse.setOrderId(item.getOrder().getId());
                itemResponse.setProductId(item.getProduct().getId());
                itemResponse.setUnitPrice(item.getUnitPrice());
                itemResponse.setDiscountApplied(item.getDiscountApplied());
                itemResponse.setQuantity(item.getQuantity());
                return itemResponse;
            }).toList();
            response.setId(order.getId());
            response.setUserId(order.getUser().getId());
            response.setTotal(order.getTotal());
            response.setStatus(order.getStatus());
            response.setCreatedAt(order.getCreatedAt());
            response.setItems(itemResponses);
            // Aquí deberías mapear los items si es necesario
            return response;
        }).toList();
        return orderResponses;

    }

    @GetMapping("/{orderId}")
    public Optional<OrderResponse> getOrderById(@PathVariable Long orderId) {

        Optional<Order> order = orderService.getOrderById(orderId);

        OrderResponse response = new OrderResponse();
        if (order.isPresent()) {
            Order foundOrder = order.get();
            response.setId(foundOrder.getId());
            response.setUserId(foundOrder.getUser().getId());
            response.setTotal(foundOrder.getTotal());
            response.setStatus(foundOrder.getStatus());
            response.setCreatedAt(foundOrder.getCreatedAt());
            // Aquí deberías mapear los items si es necesario
        }
        return Optional.of(response);
    }

    @PostMapping
    public Optional<OrderResponse> createOrder(@RequestBody OrderRequest order) {
        Optional<Order> createdOrder = orderService.createOrder(order);
        if (createdOrder.isPresent()) {
            OrderResponse response = new OrderResponse();
            Order foundOrder = createdOrder.get();
            response.setId(foundOrder.getId());
            response.setUserId(foundOrder.getUser().getId());
            response.setTotal(foundOrder.getTotal());
            response.setStatus(foundOrder.getStatus());
            response.setCreatedAt(foundOrder.getCreatedAt());
            // Aquí deberías mapear los items si es necesario
            return Optional.of(response);
        }
        return Optional.empty();
    }

    @GetMapping("/user/{userId}") // /orders/user/{userId}
    public List<OrderResponse> getOrderByUser(@PathVariable Long userId) {
        List<Order> results = orderService.getOrderByUserId(userId);
        return results.stream().map(order -> {
            OrderResponse response = new OrderResponse();
            List<OrderItem> items = order.getItems();
            List<OrderItemResponse> itemResponses = items.stream().map(item -> {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setId(item.getId());
                itemResponse.setOrderId(item.getOrder().getId());
                itemResponse.setProductId(item.getProduct().getId());
                itemResponse.setUnitPrice(item.getUnitPrice());
                itemResponse.setDiscountApplied(item.getDiscountApplied());
                itemResponse.setQuantity(item.getQuantity());
                return itemResponse;
            }).toList();
            response.setId(order.getId());
            response.setUserId(order.getUser().getId());
            response.setTotal(order.getTotal());
            response.setStatus(order.getStatus());
            response.setCreatedAt(order.getCreatedAt());
            // Aquí deberías mapear los items si es necesario
            return response;
        }).toList();
    }
}
    
