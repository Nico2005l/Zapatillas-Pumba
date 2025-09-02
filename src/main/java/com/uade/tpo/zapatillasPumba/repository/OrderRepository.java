package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("select o from Order o where o.user.id = ?1")
	List<Order> findByUserId(Long userId);

	@Query("select o from Order o where o.status = ?1")
	List<Order> findByStatus(String status);
}
