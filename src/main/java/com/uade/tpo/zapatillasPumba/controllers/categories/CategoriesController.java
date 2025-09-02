package com.uade.tpo.zapatillasPumba.controllers.categories;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.service.CategoryService;
import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(categoryService.getCategories(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(categoryService.getCategories(PageRequest.of(page, size)));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Optional<Category> result = categoryService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest)
            throws CategoryDuplicateException {
        Category result = categoryService.createCategory(categoryRequest.getDescription());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(
        @PathVariable Long categoryId,
        @RequestBody CategoryRequest categoryRequest) {
        Optional<Category> updatedCategory = categoryService.updateCategory(categoryId, categoryRequest.getDescription());
        if (updatedCategory.isPresent()) {
        return ResponseEntity.ok(updatedCategory.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        boolean deleted = categoryService.deleteCategory(categoryId);
        if (deleted) {
        return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<?> getProductsByCategory(@PathVariable Long categoryId) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
        if (categoryOpt.isPresent()) {
            return ResponseEntity.ok(categoryOpt.get().getProducts());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<?> getProductDetail(
        @PathVariable Long categoryId,
        @PathVariable Long productId) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
        if (categoryOpt.isPresent()) {
        return categoryOpt.get().getProducts().stream()
            .filter(product -> product.getId().equals(productId))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{categoryId}/products")
    public ResponseEntity<?> createProduct(
        @PathVariable Long categoryId,
        @RequestBody ProductRequest productRequest) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
        if (categoryOpt.isPresent()) {
        Category category = categoryOpt.get();
        Product createdProduct = categoryService.createProduct(category, productRequest);
        return ResponseEntity.created(URI.create("/categories/" + categoryId + "/products/" + createdProduct.getId()))
            .body(createdProduct);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<?> updateProduct(
        @PathVariable Long categoryId,
        @PathVariable Long productId,
        @RequestBody ProductRequest productRequest) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            Optional<Product> updatedProduct = categoryService.updateProduct(productId, productRequest);
            if (updatedProduct.isPresent()) {
                return ResponseEntity.ok(updatedProduct.get());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(
        @PathVariable Long categoryId,
        @PathVariable Long productId) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            boolean deleted = categoryService.deleteProduct(productId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

}

