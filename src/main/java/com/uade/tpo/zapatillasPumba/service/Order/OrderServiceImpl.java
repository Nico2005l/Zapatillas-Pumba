package com.uade.tpo.zapatillasPumba.service.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.controllers.orders.OrderRequest;
import com.uade.tpo.zapatillasPumba.controllers.orders.OrderResponse;
import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.repository.OrderRepository;
import com.uade.tpo.zapatillasPumba.repository.UserRepository;
import com.uade.tpo.zapatillasPumba.service.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderMapper orderMapper;

    public OrderServiceImpl() {
    }

    @Override
    public Optional<Order> createOrder(OrderRequest order) {
        User user = userRepository.findById(order.getUserId()).orElse(null);
        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setStatus(order.getStatus());
        newOrder.setCreatedAt(java.time.LocalDate.now());
        newOrder.setTotal(0.0);
        return Optional.of(orderRepository.save(newOrder));
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    
    // DTO methods implementation
    @Override
    public Optional<OrderResponse> createOrderResponse(OrderRequest order) {
        Optional<Order> createdOrder = createOrder(order);
        return orderMapper.toDto(createdOrder);
    }
    
    @Override
    public List<OrderResponse> getAllOrderResponses() {
        List<Order> orders = getAllOrders();
        return orderMapper.toDtoList(orders);
    }
    
    @Override
    public Optional<OrderResponse> getOrderResponseById(Long id) {
        Optional<Order> order = getOrderById(id);
        return orderMapper.toDto(order);
    }
    
    @Override
    public List<OrderResponse> getOrderResponsesByUserId(Long userId) {
        List<Order> orders = getOrderByUserId(userId);
        return orderMapper.toDtoList(orders);
    }

    @Override
    public String deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return "Orden eliminada correctamente";
        } else {
            return "Orden no encontrada";
        }
    }
}
