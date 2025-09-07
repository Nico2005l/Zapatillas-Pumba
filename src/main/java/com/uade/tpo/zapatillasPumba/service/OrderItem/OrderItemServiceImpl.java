package com.uade.tpo.zapatillasPumba.service.OrderItem;

import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemRequest;
import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.repository.OrderItemRepository;
import com.uade.tpo.zapatillasPumba.repository.OrderRepository;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;
import com.uade.tpo.zapatillasPumba.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public OrderItem createOrderItem(OrderItemRequest orderItemRequest) {
        Order order = orderRepository.findById(orderItemRequest.getOrderId()).orElse(null);
        if (order == null) {
            throw new IllegalArgumentException("Invalid order ID");
        }
        Product product = productRepository.findById(orderItemRequest.getProductId()).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        User seller = userRepository.findById(orderItemRequest.getSellerId()).orElse(null);
        if (seller == null) {
            throw new IllegalArgumentException("Invalid seller ID");
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setSeller(seller);
        orderItem.setUnitPrice(orderItemRequest.getUnitPrice());
        orderItem.setDiscountApplied(orderItemRequest.getDiscountApplied());
        orderItem.setQuantity(orderItemRequest.getQuantity());
        return orderItemRepository.save(orderItem);
    }
}
