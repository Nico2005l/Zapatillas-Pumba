package com.uade.tpo.zapatillasPumba.service.Category;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryHasProductsException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryNotFoundException;
import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    
    Category getCategoryById(Long id) throws CategoryNotFoundException;
    
    Category createCategory(String name, Long parentId) 
        throws CategoryDuplicateException, CategoryNotFoundException;
    
    Category updateCategory(Long id, String name, Long parentId) 
        throws CategoryNotFoundException;
    
    void deleteCategory(Long id) 
        throws CategoryNotFoundException, CategoryHasProductsException;
}