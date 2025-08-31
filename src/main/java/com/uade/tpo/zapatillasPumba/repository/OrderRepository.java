package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	// Buscar todas las órdenes de un usuario
	@Query("SELECT o FROM Order o WHERE o.user.id = ?1")
	java.util.List<Order> findByUserId(Long userId);

	// Buscar órdenes por cantidad
	@Query("SELECT o FROM Order o WHERE o.count = ?1")
	java.util.List<Order> findByCount(Long count);
}

