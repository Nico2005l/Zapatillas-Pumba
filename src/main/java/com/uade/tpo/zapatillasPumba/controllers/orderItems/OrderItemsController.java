package com.uade.tpo.zapatillasPumba.controllers.orderItems;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import com.uade.tpo.zapatillasPumba.service.OrderItem.OrderItemService;
import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemRequest;

@RestController
@RequestMapping("/order-items")
public class OrderItemsController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getOrderItemsByOrderId(@RequestParam Long orderId) {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemRequest orderItemRequest
    ) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItemRequest);
        return ResponseEntity.ok(createdOrderItem);
    }
    
}