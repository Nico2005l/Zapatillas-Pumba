package com.uade.tpo.zapatillasPumba.service.Category;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryHasProductsException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryNotFoundException;
import com.uade.tpo.zapatillasPumba.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category createCategory(String name, Long parentId) throws CategoryDuplicateException {
        if (categoryRepository.findByName(name).isPresent()) {
            throw new CategoryDuplicateException();
        }
        
        Category category = new Category();
        category.setName(name);
        
        if (parentId != null) {
            Category parent = categoryRepository.findById(parentId)
                .orElseThrow(CategoryNotFoundException::new);
            category.setParent(parent);
        }
        
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, String name, Long parentId) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
        
        category.setName(name);
        
        if (parentId != null) {
            Category parent = categoryRepository.findById(parentId)
                .orElseThrow(CategoryNotFoundException::new);
            category.setParent(parent);
        } else {
            category.setParent(null);
        }
        
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
        
        // Verificar si la categoría tiene productos asociados
        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new CategoryHasProductsException();
        }
        
        // No tiene productos, se puede eliminar
        categoryRepository.delete(category);
    }
}
