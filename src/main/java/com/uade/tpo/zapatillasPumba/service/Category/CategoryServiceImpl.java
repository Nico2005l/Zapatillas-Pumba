package com.uade.tpo.zapatillasPumba.service.Category;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryHasProductsException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryNotFoundException;
import com.uade.tpo.zapatillasPumba.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/* Aca les dejo la implementacion del servicio de categorias
 * como dije en el controlador, intentemos hacer el resto parecido a esto en lo posible
 */

// La anotacion @Service indica que esta clase es un servicio de Spring
@Service
public class CategoryServiceImpl implements CategoryService {

    // Inyectamos el repositorio de categorias para poder utilizarlo en los metodos
    @Autowired
    private CategoryRepository categoryRepository;


    // Voy a hacer un paso a paso de este metodo para que se entienda bien al ser el primero
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll(); // Obtenemos todas las categorias de la base de datos
        categories.removeIf(category -> category.getParent() != null); // Sacamos las que tienen padre, osea nos quedamos solo con las categorias raiz
        for (Category parent : categories) { // Por cada categoria raiz, le seteamos sus hijos
            parent.setChildren(
                categoryRepository.findAll().stream() // Recorremos todas las categorias de nuevo y le filtramos las que tienen como padre a la categoria raiz actual
                    .filter(child -> child.getParent() != null && child.getParent().getId().equals(parent.getId()))
                    .toList()
            );
        } // Todo esto lo hacemos para que al pedir las categorias, vengan con sus hijos ya seteados y no se repitan
        return categories;
    }

    /*
     * Este metodo obtiene una categoria por su id
     * Si no la encuentra, lanza una excepcion CategoryNotFoundException
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
    }

    public Category createCategory(String name, Long parentId) throws CategoryDuplicateException { // Si ya existe una categoria con ese nombre, lanza una excepcion CategoryDuplicateException
        if (categoryRepository.findByName(name).isPresent()) {
            throw new CategoryDuplicateException();
        }
        
        Category category = new Category(); // Creamos una nueva categoria y le seteamos sus atributos
        category.setName(name);
        
        if (parentId != null) { // si el parent id no es nulo, buscamos la categoria padre y se la seteamos
            Category parent = categoryRepository.findById(parentId) // si no existe la categoria padre, lanza una excepcion CategoryNotFoundException
                .orElseThrow(CategoryNotFoundException::new);
            category.setParent(parent);
        }
        
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, String name, Long parentId) { // es exactamente igual a createCategory, pero primero busca la categoria a modificar por ID
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

    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
        
        if (category.getProducts() != null && !category.getProducts().isEmpty()) { // Verificamos si la categoría tiene una lista de productos y si esta tiene productos asociados, si los tiene lanzamos una excepción
            throw new CategoryHasProductsException();
        }

        categoryRepository.delete(category);
        return "Categoría eliminada correctamente";
    }
}
