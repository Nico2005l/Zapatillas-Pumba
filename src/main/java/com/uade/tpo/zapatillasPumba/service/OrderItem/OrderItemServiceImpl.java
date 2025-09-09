package com.uade.tpo.zapatillasPumba.service.OrderItem;

import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemRequest;
import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import com.uade.tpo.zapatillasPumba.entity.Product;
<<<<<<< HEAD
import com.uade.tpo.zapatillasPumba.entity.User;
import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.repository.DiscountRepository;
=======
>>>>>>> ca69470fc8e449ca27014f4b19b92887ff0b36c5
import com.uade.tpo.zapatillasPumba.repository.OrderItemRepository;
import com.uade.tpo.zapatillasPumba.repository.OrderRepository;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;

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

<<<<<<< HEAD
    @Autowired
    private DiscountRepository discountRepository;  

    @Autowired
    private UserRepository userRepository;

=======
>>>>>>> ca69470fc8e449ca27014f4b19b92887ff0b36c5
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
        } else if (product.getStock() < orderItemRequest.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock for product ID: " + product.getId());
        } else {
            product.setStock(product.getStock() - orderItemRequest.getQuantity());
            productRepository.save(product);
        }
        OrderItem orderItem = new OrderItem();

        Discount discount = discountRepository.findByProductId(product.getId()).get(0);
        
        orderItem.setDiscountApplied(discount.getValue());
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setUnitPrice(orderItemRequest.getUnitPrice());
        orderItem.setQuantity(orderItemRequest.getQuantity());
        return orderItemRepository.save(orderItem);
    }
}
