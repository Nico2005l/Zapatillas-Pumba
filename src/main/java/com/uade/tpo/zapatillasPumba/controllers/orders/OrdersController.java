package com.uade.tpo.zapatillasPumba.controllers.orders;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return orderService.getAllOrderResponses();
    }

    @GetMapping("/{orderId}")
    public Optional<OrderResponse> getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderResponseById(orderId);
    }

    @PostMapping
    public Optional<OrderResponse> createOrder(@RequestBody OrderRequest order) {
        return orderService.createOrderResponse(order);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrderByUser(@PathVariable Long userId) {
        return orderService.getOrderResponsesByUserId(userId);
    }
}
    
