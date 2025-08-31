package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	// Buscar todas las imágenes de un producto
	@Query("SELECT pi FROM ProductImage pi WHERE pi.product.id = ?1")
	List<ProductImage> findByProductId(Long productId);
}
