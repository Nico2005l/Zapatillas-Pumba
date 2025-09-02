package com.uade.tpo.zapatillasPumba.controllers.categories;

import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private Long parentId;
}
