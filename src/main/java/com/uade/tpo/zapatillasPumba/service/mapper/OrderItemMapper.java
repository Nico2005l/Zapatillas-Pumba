package com.uade.tpo.zapatillasPumba.service.mapper;

import org.springframework.stereotype.Component;
import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemResponse;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    
    /**
     * Maps an OrderItem entity to an OrderItemResponse DTO
     * @param orderItem The entity to map
     * @return The mapped DTO
     */
    public OrderItemResponse toDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        
        OrderItemResponse response = new OrderItemResponse();
        response.setId(orderItem.getId());
        response.setOrderId(orderItem.getOrder().getId());
        response.setProductId(orderItem.getProduct().getId());
        response.setUnitPrice(orderItem.getProduct().getPrice());
        response.setDiscountApplied(orderItem.getDiscountApplied());
        response.setQuantity(orderItem.getQuantity());
        
        // Calculate derived values
        double subTotal = response.getUnitPrice() * response.getQuantity();
        response.setSubTotal(subTotal);
        response.setTotal(subTotal - (response.getDiscountApplied() * subTotal));
        
        return response;
    }
    
    /**
     * Maps a list of OrderItem entities to a list of OrderItemResponse DTOs
     * @param orderItems The list of entities to map
     * @return The list of mapped DTOs
     */
    public List<OrderItemResponse> toDtoList(List<OrderItem> orderItems) {
        if (orderItems == null) {
            return null;
        }
        
        return orderItems.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
