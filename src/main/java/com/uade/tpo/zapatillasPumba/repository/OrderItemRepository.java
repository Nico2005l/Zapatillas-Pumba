package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	// Buscar todos los items de una orden
	List<OrderItem> findByOrderId(Long orderId);

	// Buscar todos los items de un producto
	List<OrderItem> findByProductId(Long productId);

	// Buscar todos los items por vendedor
	List<OrderItem> findBySellerId(Long sellerId);
}
