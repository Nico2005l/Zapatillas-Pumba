package com.uade.tpo.zapatillasPumba.service.Category;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.SexoCategory;
import com.uade.tpo.zapatillasPumba.entity.TipoCategory;
import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategoryById(Long categoryId);
    Category createCategory(SexoCategory sexo, TipoCategory tipo);
    Category createCategory(SexoCategory sexo, TipoCategory tipo, Long parentId);
    Category updateCategory(Long categoryId, SexoCategory sexo, TipoCategory tipo);
    String deleteCategory(Long categoryId);
}