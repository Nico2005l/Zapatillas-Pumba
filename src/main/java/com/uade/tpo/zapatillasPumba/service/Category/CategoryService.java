package com.uade.tpo.zapatillasPumba.service.Category;

import com.uade.tpo.zapatillasPumba.controllers.categories.CategoryRequest;
import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryHasProductsException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryNotFoundException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategories();

    Optional<Category> getCategoryById(Long id) throws CategoryNotFoundException;

    Category createCategory(CategoryRequest categoryRequest)
        throws CategoryDuplicateException, CategoryNotFoundException;

    Category updateCategory(Long id, CategoryRequest categoryRequest)
        throws CategoryNotFoundException;
    
    String deleteCategory(Long id)
        throws CategoryNotFoundException, CategoryHasProductsException;
}