
package com.uade.tpo.zapatillasPumba.service.Category;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;

public interface CategoryService {
    public Page<Category> getCategories(PageRequest pageRequest);

    public Optional<Category> getCategoryById(Long categoryId);

    public Category createCategory(String description) throws CategoryDuplicateException;

    public Optional<Category> updateCategory(Long categoryId, String description);

    public boolean deleteCategory(Long categoryId);
}