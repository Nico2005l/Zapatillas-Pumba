package com.uade.tpo.zapatillasPumba.service.OrderItem;

import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemRequest;
import com.uade.tpo.zapatillasPumba.controllers.orderItems.OrderItemResponse;
import com.uade.tpo.zapatillasPumba.entity.Order;
import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.repository.DiscountRepository;
import com.uade.tpo.zapatillasPumba.repository.OrderItemRepository;
import com.uade.tpo.zapatillasPumba.repository.OrderRepository;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;
import com.uade.tpo.zapatillasPumba.service.mapper.OrderItemMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DiscountRepository discountRepository;
    
    @Autowired
    private OrderItemMapper orderItemMapper;
    
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

        Optional<Discount> discount = discountRepository.findByProductId(product.getId()).stream().findFirst();

        orderItem.setDiscountApplied(discount.map(Discount::getValue).orElse(0.0));
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemRequest.getQuantity());

        order.setTotal(order.getTotal() + (product.getPrice() * orderItemRequest.getQuantity() - (orderItem.getDiscountApplied() * product.getPrice() * orderItemRequest.getQuantity())));
        return orderItemRepository.save(orderItem);
    }
    
    @Override
    public List<OrderItemResponse> getOrderItemResponsesByOrderId(Long orderId) {
        List<OrderItem> items = getOrderItemsByOrderId(orderId);
        return orderItemMapper.toDtoList(items);
    }
    
    @Override
    public OrderItemResponse createOrderItemResponse(OrderItemRequest orderItemRequest) {
        OrderItem createdItem = createOrderItem(orderItemRequest);
        return orderItemMapper.toDto(createdItem);
    }

    @Override
    public String deleteOrderItem(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return "Item eliminado correctamente";
        } else {
            return "Item no encontrado";
        }
    }
}
