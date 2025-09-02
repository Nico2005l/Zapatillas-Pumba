package com.uade.tpo.zapatillasPumba.service.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl() {
    }

    @Override
    public Optional<Order> createOrder(Order order) {
        return Optional.of(orderRepository.save(order));
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
    public Optional<Order> updateOrder(Long id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(id);
            return Optional.of(orderRepository.save(order));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
