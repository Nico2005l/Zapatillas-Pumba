package com.uade.tpo.zapatillasPumba.service.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.controllers.orders.OrderRequest;
import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.repository.OrderRepository;
import com.uade.tpo.zapatillasPumba.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public OrderServiceImpl() {
    }

    @Override
    public Optional<Order> createOrder(OrderRequest order) {
        User user = userRepository.findById(order.getUserId()).orElse(null);
        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setStatus(order.getStatus());
        newOrder.setCreatedAt(java.time.LocalDateTime.now());
        newOrder.setTotal(order.getTotal());
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

}
