package com.uade.tpo.zapatillasPumba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.zapatillasPumba.controllers.categories.ProductRequest;
import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.repository.CategoryRepository;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Page<Category> getCategories(PageRequest pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Category createCategory(String name) throws CategoryDuplicateException {
        List<Category> categories = categoryRepository.findByName(name);
        if (categories.isEmpty()) {
            categoryRepository.save(new Category(name));
        }

        throw new CategoryDuplicateException();
    }

    @Transactional(rollbackFor = Throwable.class)
    public Optional<Category> updateCategory(Long categoryId, String description) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            category.get().setName(description);
            categoryRepository.save(category.get());
            return category;
        }
        return Optional.empty();
    }

    public boolean deleteCategory(Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    public boolean deleteProduct(Long productId) {
        if (productRepository.existsProductById(productId)) {
            productRepository.deleteProductById(productId);
            return true;
        }
        return false;
    }

    public Optional<Product> updateProduct(Long productId, ProductRequest productRequest) {
        Optional<Product> product = productRepository.findProductById(productId);
        if (product.isPresent()) {
            product.get().setTitle(productRequest.getTitle());
            product.get().setDescription(productRequest.getDescription());
            product.get().setPrice(productRequest.getPrice());
            productRepository.save(product.get());
            return product;
        }
        return Optional.empty();
    }

    @Override
    public Product createProduct(Category category, ProductRequest productRequest) {
        Product product = new Product();
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);
        product = productRepository.save(product);
        category.getProducts().add(product);
        categoryRepository.save(category);
        return product;
    }

}
