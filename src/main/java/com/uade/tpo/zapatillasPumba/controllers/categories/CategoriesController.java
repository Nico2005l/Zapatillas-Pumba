package com.uade.tpo.zapatillasPumba.controllers.categories;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryHasProductsException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryNotFoundException;
import com.uade.tpo.zapatillasPumba.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    /**
     * GET /categories/{categoryId} : Get a single category by its ID.
     * @param categoryId The ID of the category.
     * @return The category if found.
     * @throws CategoryNotFoundException if category doesn't exist.
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    /**
     * POST /categories : Create a new category.
     * Note: Authorization for admin roles should be handled by a security layer.
     * @param categoryRequest The category data.
     * @return The created category with a 201 Created status.
     * @throws CategoryDuplicateException if a category with the same name already exists.
     * @throws CategoryNotFoundException if parent category doesn't exist.
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category result = categoryService.createCategory(categoryRequest.getName(), categoryRequest.getParentId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    /**
     * PUT /categories/{categoryId} : Update an existing category.
     * Note: Authorization for admin roles should be handled by a security layer.
     * @param categoryId The ID of the category to update.
     * @param categoryRequest The new category data.
     * @return The updated category.
     * @throws CategoryNotFoundException if category doesn't exist.
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest categoryRequest) {
        Category updatedCategory = categoryService.updateCategory(
                categoryId,
                categoryRequest.getName(),
                categoryRequest.getParentId());
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * DELETE /categories/{categoryId} : Delete a category.
     * Note: Authorization for admin roles should be handled by a security layer.
     * @param categoryId The ID of the category to delete.
     * @return 204 No Content on successful deletion.
     * @throws CategoryNotFoundException if category doesn't exist.
     * @throws CategoryHasProductsException if the category has products and cannot be deleted.
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}

