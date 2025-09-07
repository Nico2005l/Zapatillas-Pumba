package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.Discount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
	@Query("select d from Discount d where d.product.id = ?1")
	List<Discount> findByProductId(Long productId);

	@Query("select d from Discount d where d.isActive = true")
	List<Discount> findActiveDiscounts();

	
}
