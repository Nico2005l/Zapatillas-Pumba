package com.uade.tpo.zapatillasPumba.service.Category;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.SexoCategory;
import com.uade.tpo.zapatillasPumba.entity.TipoCategory;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryDuplicateException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryHasProductsException;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryNotFoundException;
import com.uade.tpo.zapatillasPumba.exceptions.InvalidCategoryParentException;
import com.uade.tpo.zapatillasPumba.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        for (Category parent : categories) { // Por
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
            .orElseThrow(() -> new CategoryNotFoundException("Categoría con id " + id + " no encontrada"));
    }

    @Override
    public Category createCategory(SexoCategory sexo, TipoCategory tipo) {
        // Validate that only one field is provided
        if ((sexo == null && tipo == null) || (sexo != null && tipo != null)) {
            throw new IllegalArgumentException("Debe especificar exactamente uno: sexo O tipo");
        }

        // Check for duplicates - gender categories must be root
        if (sexo != null && categoryRepository.existsBySexoAndParentIsNull(sexo)) {
            throw new CategoryDuplicateException(
                String.format("Ya existe una categoría raíz con el género %s", sexo.getDisplayName())
            );
        }

        // Type categories cannot be created without a parent
        if (tipo != null) {
            throw new InvalidCategoryParentException(
                "Las categorías de tipo deben tener una categoría padre de género"
            );
        }

        Category category = new Category(sexo, tipo);
        return categoryRepository.save(category);
    }

    @Override
    public Category createCategory(SexoCategory sexo, TipoCategory tipo, Long parentId) {
        // Validate inputs
        if ((sexo == null && tipo == null) || (sexo != null && tipo != null)) {
            throw new IllegalArgumentException("Debe especificar exactamente uno: sexo O tipo");
        }

        // Check for duplicates
        if (sexo != null) {
            // Check duplicate gender category (must be root)
            if (categoryRepository.existsBySexoAndParentIsNull(sexo)) {
                throw new CategoryDuplicateException(
                    String.format("Ya existe una categoría raíz con el género %s", sexo.getDisplayName())
                );
            }
        }

        if (tipo != null) {
            if (parentId == null) {
                throw new InvalidCategoryParentException("Las categorías de tipo deben tener una categoría padre de género");
            }
            // Check duplicate type under specific parent
            if (categoryRepository.existsByTipoAndParentId(tipo, parentId)) {
                throw new CategoryDuplicateException(
                    String.format("Ya existe la categoría tipo %s bajo el padre especificado", tipo.getDisplayName())
                );
            }
            
            Category parent = getCategoryById(parentId);
            if (parent.getSexo() == null) {
                throw new InvalidCategoryParentException("La categoría padre debe ser de tipo género");
            }
            
            Category category = new Category(sexo, tipo);
            category.setParent(parent);
            return categoryRepository.save(category);
        }

        return categoryRepository.save(new Category(sexo, tipo));
    }

    public Category updateCategory(Long categoryId, SexoCategory sexo, TipoCategory tipo) {
        Category existingCategory = getCategoryById(categoryId);

        // Validate that only one field is provided
        if ((sexo == null && tipo == null) || (sexo != null && tipo != null)) {
            throw new IllegalArgumentException("Debe especificar exactamente uno: sexo O tipo");
        }

        // Check if trying to convert gender to type
        if (existingCategory.getSexo() != null && tipo != null) {
            if (!existingCategory.getChildren().isEmpty()) {
                throw new InvalidCategoryParentException(
                    "No se puede convertir una categoría padre en subcategoría mientras tenga subcategorías"
                );
            }
        }

        // Check if trying to convert type to gender
        if (existingCategory.getTipo() != null && sexo != null) {
            if (!existingCategory.getProducts().isEmpty()) {
                throw new InvalidCategoryParentException(
                    "No se puede convertir una subcategoría en categoría padre mientras tenga productos"
                );
            }
        }

        // Check for duplicates if changing to another gender
        if (sexo != null && !sexo.equals(existingCategory.getSexo())) {
            if (categoryRepository.existsBySexoAndParentIsNull(sexo)) {
                throw new CategoryDuplicateException(
                    String.format("Ya existe una categoría raíz con el género %s", sexo.getDisplayName())
                );
            }
        }

        // Check for duplicates if changing to another type under same parent
        if (tipo != null && !tipo.equals(existingCategory.getTipo())) {
            if (categoryRepository.existsByTipoAndParentId(tipo, existingCategory.getParent().getId())) {
                throw new CategoryDuplicateException(
                    String.format("Ya existe una categoría tipo %s bajo el mismo padre", tipo.getDisplayName())
                );
            }
        }

        existingCategory.setSexo(sexo);
        existingCategory.setTipo(tipo);

        return categoryRepository.save(existingCategory);
    }

    @Override
    public String deleteCategory(Long id) {
        Category category = getCategoryById(id);
        
        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new CategoryHasProductsException("No se puede eliminar la categoría porque tiene productos asociados");
        }
        
        categoryRepository.delete(category);
        return "Categoría eliminada correctamente";
    }
}
