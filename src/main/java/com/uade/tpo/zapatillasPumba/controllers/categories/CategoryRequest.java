package com.uade.tpo.zapatillasPumba.controllers.categories;

import com.uade.tpo.zapatillasPumba.entity.SexoCategory;
import com.uade.tpo.zapatillasPumba.entity.TipoCategory;
import lombok.Data;

@Data
public class CategoryRequest {
    private SexoCategory sexo;
    private TipoCategory tipo;
    private Long parentId;
}
