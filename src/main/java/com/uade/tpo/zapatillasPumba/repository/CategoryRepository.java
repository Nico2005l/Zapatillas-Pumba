package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.SexoCategory;
import com.uade.tpo.zapatillasPumba.entity.TipoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // For root categories (gender)
    boolean existsBySexoAndParentIsNull(SexoCategory sexo);
    
    // For type categories under a parent
    boolean existsByTipoAndParentId(TipoCategory tipo, Long parentId);
    
    // Get all root categories
    @Query("SELECT c FROM Category c WHERE c.parent IS NULL")
    List<Category> findRootCategories();
}
