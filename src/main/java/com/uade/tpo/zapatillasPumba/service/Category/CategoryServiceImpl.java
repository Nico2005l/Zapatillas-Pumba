package com.uade.tpo.zapatillasPumba.service.Category;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
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
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(String name, Long parentId) throws CategoryDuplicateException {
        if (categoryRepository.findByName(name).isPresent()) {
            throw new CategoryDuplicateException();
        }
        Category category = new Category();
        category.setName(name);
        if (parentId != null) {
            categoryRepository.findById(parentId).ifPresent(category::setParent);
        }
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> updateCategory(Long id, String name, Long parentId) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(name);
            if (parentId != null) {
                categoryRepository.findById(parentId).ifPresent(category::setParent);
            } else {
                category.setParent(null);
            }
            return categoryRepository.save(category);
        });
    }

    @Override
    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
