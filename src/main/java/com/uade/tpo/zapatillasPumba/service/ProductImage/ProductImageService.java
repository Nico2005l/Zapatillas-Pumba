package com.uade.tpo.zapatillasPumba.service.ProductImage;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> getImagesByProductId(Long productId);
    ProductImage getProductImageById(Long imageId);
    void deleteProductImage(Long imageId);
    ProductImage uploadProductImage(Long productId, MultipartFile file);
    
    // Método para verificar si una imagen pertenece a un producto específico
    boolean imageExistsForProduct(Long productId, Long imageId);
}