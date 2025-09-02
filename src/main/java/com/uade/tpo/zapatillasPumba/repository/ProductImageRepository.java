package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	@Query("select pi from ProductImage pi where pi.product.id = ?1")
	List<ProductImage> findByProductId(Long productId);
}
