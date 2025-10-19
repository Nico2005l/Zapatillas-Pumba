package com.uade.tpo.zapatillasPumba.controllers.categories;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest)
            throws CategoryDuplicateException {
        Category result = categoryService.createCategory(
                categoryRequest.getSexo(), 
                categoryRequest.getTipo(),
                categoryRequest.getParentId()
        );
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest categoryRequest) {
        Category updatedCategory = categoryService.updateCategory(
                categoryId,
                categoryRequest.getSexo(),
                categoryRequest.getTipo()
        );
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        String mensaje = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(mensaje);
    }
}

