package com.uade.tpo.zapatillasPumba.controllers.products;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;

import lombok.Data;

@Data
public class ProductRequest {
    private String title;
    private String description;
    private Double price;
    private Integer stock;
    private Boolean isVisible;
    private Long categoryId;
    private Long sellerId;
    private List<ProductImage> productImages;
}
