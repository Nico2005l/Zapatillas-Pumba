package com.uade.tpo.zapatillasPumba.controllers.categories;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*  !! IMPORTANTE !!
 * Aca les dejo el controlador de categorias,
 * intentemos hacer el resto parecido a esto en lo posible
 * Al ser el primero dejo algunos comentarios de mas asi se entiende para poder realizar el resto
 */


/* Las anotaciones indican que es un controlador REST que va a estar resolviendo requests a /categories */
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    /* Inyectamos el servicio de categorias para poder utilizarlo en los endpoints */
    @Autowired
    private CategoryService categoryService;

    /* Creamos un endpoint para obtener todas las categorias creadas
     * Se hace un GET a /categories
     * Devuelve un ResponseEntity con un listado de categorias y el estado 200 OK (el .ok indica que la respuesta devuelve un 200) */
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    /* Creamos un endpoint para obtener una categoria por su id
     * Se hace un GET a /categories/{categoryId}, esta con corchetes porque es variable, tipo puede ser 1 o 2 etc...
     * Devuelve un ResponseEntity con la categoria y el estado 200 OK
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) { //PathVariable indica que el valor de categoryId viene de la URL
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    /* Primer endpoint que hace un POST, osea crea una categoria
     * Se hace un POST a /categories
     * Devuelve un ResponseEntity con la categoria creada y el estado 201 Created
     */
    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) //RequestBody indica que el valor de categoryRequest viene del body del request
            throws CategoryDuplicateException {
        Category result = categoryService.createCategory(categoryRequest.getName(), categoryRequest.getParentId()); //Creamos la categoria con el servicio, instertando como parametro el nombre y el id del padre (si es que tiene) del request
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result); // El .created indica un 201. El URI es para indicar la ubicacion de la nueva categoria creada y el .body(result) es para devolver la categoria creada en el body de la respuesta
    }

    /* Primer endpoint que hace un PUT, osea modifica una categoria
     * Se hace un PUT a /categories/{categoryId}
     * Devuelve un ResponseEntity con la categoria modificada y el estado 200 OK
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest categoryRequest) { //El put requiere un ID y tambien un body para modificarlo. Es como combinar el GET por ID y el POST
        Category updatedCategory = categoryService.updateCategory(
                categoryId,
                categoryRequest.getName(),
                categoryRequest.getParentId());
        return ResponseEntity.ok(updatedCategory);
    }

    /* Primer endpoint que hace un DELETE, osea borra una categoria
     * Se hace un DELETE a /categories/{categoryId}
     * Devuelve un ResponseEntity indicando un 204 No Content (el .noContent indica un 204)
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}

