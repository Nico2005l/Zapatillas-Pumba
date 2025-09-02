package com.uade.tpo.zapatillasPumba.controllers.productImages;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import com.uade.tpo.zapatillasPumba.controllers.productImages.ProductImageRequest;
import com.uade.tpo.zapatillasPumba.service.ProductImageService;

@RestController
@RequestMapping("/product-images")
public class ProductImagesController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping
    public List<ProductImage> getAllProductImages() {
        return productImageService.getAllProductImages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductImage> getProductImageById(@PathVariable Long id) {
        ProductImage image = productImageService.getProductImageById(id);
        return image != null ? ResponseEntity.ok(image) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductImage(@PathVariable Long id, @RequestBody ProductImageRequest request) {
        productImageService.updateProductImage(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductImage(@PathVariable Long id) {
        productImageService.deleteProductImage(id);
        return ResponseEntity.ok().build();
    }
}