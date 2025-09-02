package com.uade.tpo.zapatillasPumba.controllers.categories;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    /**
     * GET /categories : List all categories.
     * @return A list of all categories.
     */
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    /**
     * GET /categories/{categoryId} : Get a single category by its ID.
     * @param categoryId The ID of the category.
     * @return The category if found, otherwise 404 Not Found.
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Optional<Category> result = categoryService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    /**
     * POST /categories : Create a new category.
     * Note: Authorization for admin roles should be handled by a security layer.
     * @param categoryRequest The cate  gory data.
     * @return The created category with a 201 Created status.
     * @throws CategoryDuplicateException if a category with the same name already exists.
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest)
            throws CategoryDuplicateException {
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
     * @return The updated category, or 404 Not Found if the category doesn't exist.
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryId, categoryRequest.getName(), categoryRequest.getParentId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /categories/{categoryId} : Delete a category.
     * Note: Authorization for admin roles should be handled by a security layer.
     * @param categoryId The ID of the category to delete.
     * @return 204 No Content on successful deletion, or 404 Not Found.
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        boolean deleted = categoryService.deleteCategory(categoryId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

