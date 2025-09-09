package com.uade.tpo.zapatillasPumba.controllers.orderItems;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import com.uade.tpo.zapatillasPumba.service.OrderItem.OrderItemService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemRequest;

@RestController
@RequestMapping("/order-items")
public class OrderItemsController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemResponse> getOrderItemsByOrderId(@RequestParam Long orderId) {

       List<OrderItemResponse> orderItemResponses = new ArrayList<>();
       for (OrderItem item : orderItemService.getOrderItemsByOrderId(orderId)) {
           OrderItemResponse response = new OrderItemResponse();
           response.setId(item.getId());
           response.setOrderId(item.getOrder().getId());
           response.setProductId(item.getProduct().getId());
           response.setUnitPrice(item.getProduct().getPrice());
           response.setDiscountApplied(item.getDiscountApplied());
           response.setQuantity(item.getQuantity());
           orderItemResponses.add(response);
       }
        return orderItemResponses;
    }

    @PostMapping
    public OrderItemResponse createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {

        OrderItemResponse createdOrderItem = new OrderItemResponse();
        OrderItem orderItem = orderItemService.createOrderItem(orderItemRequest);
        createdOrderItem.setId(orderItem.getId());
        createdOrderItem.setOrderId(orderItem.getOrder().getId());
        createdOrderItem.setProductId(orderItem.getProduct().getId());
        createdOrderItem.setUnitPrice(orderItem.getProduct().getPrice());
        createdOrderItem.setDiscountApplied(orderItem.getDiscountApplied());
        createdOrderItem.setQuantity(orderItem.getQuantity());
        createdOrderItem.setSubTotal(createdOrderItem.getUnitPrice() * createdOrderItem.getQuantity());
        createdOrderItem.setTotal(createdOrderItem.getSubTotal() - (createdOrderItem.getDiscountApplied() * createdOrderItem.getSubTotal()));

        return createdOrderItem;
    }
    
}