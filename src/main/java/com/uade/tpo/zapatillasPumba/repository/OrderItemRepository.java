package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	// Buscar todos los items de una orden
	@Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = ?1")
	List<OrderItem> findByOrderId(Long orderId);

	// Buscar todos los items de un producto
	@Query("SELECT oi FROM OrderItem oi WHERE oi.product.id = ?1")
	List<OrderItem> findByProductId(Long productId);

	// Buscar todos los items por vendedor
	@Query("SELECT oi FROM OrderItem oi WHERE oi.seller.id = ?1")
	List<OrderItem> findBySellerId(Long sellerId);
}
