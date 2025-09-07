package com.uade.tpo.zapatillasPumba.controllers.productImages;

import lombok.Data;

@Data
public class ProductImageRequest {
    // Eliminamos productId ya que vendrá en la URL
    // Solo mantenemos otros campos que podríamos necesitar
    private String imageUrl;
}