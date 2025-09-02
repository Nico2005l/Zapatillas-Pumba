
package com.uade.tpo.zapatillasPumba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.zapatillasPumba.controllers.categories.ProductRequest;
import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;

public interface CategoryService {
    public Page<Category> getCategories(PageRequest pageRequest);

    public Optional<Category> getCategoryById(Long categoryId);

    public Category createCategory(String description) throws CategoryDuplicateException;

    public Optional<Category> updateCategory(Long categoryId, String description);

    public boolean deleteCategory(Long categoryId);

    public Product createProduct(Category category, ProductRequest productRequest);

    public Optional<Product> getProductById(Long productId);

    public boolean deleteProduct(Long productId);

    public Optional<Product> updateProduct(Long productId, ProductRequest productRequest);
}