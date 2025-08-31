package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	// Buscar todas las órdenes de un usuario
	java.util.List<Order> findByUserId(Long userId);

	// Buscar órdenes por cantidad
	java.util.List<Order> findByCount(Long count);
}

