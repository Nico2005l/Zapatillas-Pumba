package com.uade.tpo.zapatillasPumba.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.controllers.orders.OrderResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    
    @Autowired
    private OrderItemMapper orderItemMapper;
    
    /**
     * Maps an Order entity to an OrderResponse DTO
     * @param order The entity to map
     * @return The mapped DTO
     */
    public OrderResponse toDto(Order order) {
        if (order == null) {
            return null;
        }
        
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setTotal(order.getTotal());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        
        // Map order items using the OrderItemMapper
        if (order.getItems() != null) {
            response.setItems(orderItemMapper.toDtoList(order.getItems()));
        }
        
        return response;
    }
    
    /**
     * Maps an optional Order entity to an optional OrderResponse DTO
     * @param orderOptional The optional entity to map
     * @return The optional mapped DTO
     */
    public Optional<OrderResponse> toDto(Optional<Order> orderOptional) {
        if (orderOptional == null || orderOptional.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(toDto(orderOptional.get()));
    }
    
    /**
     * Maps a list of Order entities to a list of OrderResponse DTOs
     * @param orders The list of entities to map
     * @return The list of mapped DTOs
     */
    public List<OrderResponse> toDtoList(List<Order> orders) {
        if (orders == null) {
            return null;
        }
        
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
