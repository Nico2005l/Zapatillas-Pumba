package com.uade.tpo.zapatillasPumba.controllers.products;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import com.uade.tpo.zapatillasPumba.entity.TalleEnum;
import com.uade.tpo.zapatillasPumba.entity.ColorEnum;

import lombok.Data;

@Data
public class ProductRequest {
    private String title;
    private String descripcionCorta;
    private String descripcionLarga;
    private Double price;
    private Integer stock;
    private Boolean isVisible;
    private Long categoryId;
    private List<ProductImage> productImages;
    private TalleEnum talle;
    private ColorEnum color;
}
