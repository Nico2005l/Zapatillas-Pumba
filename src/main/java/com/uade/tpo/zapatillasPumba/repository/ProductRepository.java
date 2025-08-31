package com.uade.tpo.zapatillasPumba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.zapatillasPumba.entity.Product;

@Repository
public class ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE p.category.id =  ?1")
    List<Product> findByCategoryId(Long categoryId);

}
