package com.uade.tpo.zapatillasPumba.service.Category;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategories();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(String name, Long parentId) throws CategoryDuplicateException;
    Optional<Category> updateCategory(Long id, String name, Long parentId);
    boolean deleteCategory(Long id);
}