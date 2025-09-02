package com.uade.tpo.zapatillasPumba.service.ProductImage;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import java.util.List;

public interface ProductImageService {
    List<ProductImage> getAllProductImages();
    ProductImage getProductImageById(Long id);
    ProductImage createProductImage(ProductImage productImage);
    ProductImage updateProductImage(Long id, ProductImage productImage);
    void deleteProductImage(Long id);
}
