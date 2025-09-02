package com.uade.tpo.zapatillasPumba.repository;


import com.uade.tpo.zapatillasPumba.entity.Product;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    List<Product> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Optional<Product> findProductById(Long productId);

    @Query("SELECT p FROM Product p WHERE (:category IS NULL OR p.category.name = :category) AND (:seller IS NULL OR p.seller = :seller) AND (:isVisible IS NULL OR p.isVisible = :isVisible)")
    List<Product> findByCategoryAndSellerAndIsVisible(String category, String seller, Boolean isVisible);

    boolean existsProductById(Long productId);

    void deleteProductById(Long productId);
}
