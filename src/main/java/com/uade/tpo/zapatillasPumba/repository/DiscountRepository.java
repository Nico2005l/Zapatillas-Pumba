package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
	// Buscar todos los descuentos de un producto
	List<Discount> findByProductId(Long productId);
	// Buscar descuentos activos
	List<Discount> findByIsActiveTrue();
}


