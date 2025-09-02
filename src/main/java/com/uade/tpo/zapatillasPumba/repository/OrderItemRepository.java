package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	@Query("select oi from OrderItem oi where oi.order.id = ?1")
	List<OrderItem> findByOrderId(Long orderId);

	@Query("select oi from OrderItem oi where oi.product.id = ?1")
	List<OrderItem> findByProductId(Long productId);

	@Query("select oi from OrderItem oi where oi.seller.id = ?1")
	List<OrderItem> findBySellerId(Long sellerId);
}
