package com.uade.tpo.zapatillasPumba.controllers.orderItems;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.uade.tpo.zapatillasPumba.service.OrderItem.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderItemsController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemResponse> getOrderItemsByOrderId(@RequestParam Long orderId) {
        // Delegating the mapping to the service layer
        return orderItemService.getOrderItemResponsesByOrderId(orderId);
    }

    @PostMapping
    public OrderItemResponse createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        return orderItemService.createOrderItemResponse(orderItemRequest);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.ok("Order item eliminado correctamente");
    }

}