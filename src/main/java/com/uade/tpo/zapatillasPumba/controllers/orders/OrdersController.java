package com.uade.tpo.zapatillasPumba.controllers.orders;


import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.service.Order.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

   
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        OrderResponse response = new OrderResponse(orders);
        return ResponseEntity.ok(response.getOrders());

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Optional<Order> createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder.orElse(null));
    }

    @GetMapping("/user/{userId}") // /orders/user/{userId}
    public ResponseEntity<List<Order>> getOrderByUser(@PathVariable Long userId) {
        List<Order> results = orderService.getOrderByUserId(userId);
        OrderResponse response = new OrderResponse(results);
        return ResponseEntity.ok(response.getOrders());
    }
}
    
