package com.uade.tpo.zapatillasPumba.service.OrderItem;

import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import com.uade.tpo.zapatillasPumba.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}
