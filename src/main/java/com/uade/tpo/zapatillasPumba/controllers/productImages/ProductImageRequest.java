package com.uade.tpo.zapatillasPumba.controllers.productImages;

import lombok.Data;

@Data
public class ProductImageRequest {
    private Long productId;
    private String imageUrl;
}