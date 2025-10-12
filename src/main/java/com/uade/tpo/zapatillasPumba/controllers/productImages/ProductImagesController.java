package com.uade.tpo.zapatillasPumba.controllers.productImages;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import com.uade.tpo.zapatillasPumba.service.ProductImage.ProductImageService;

@RestController
@RequestMapping("/products/{productId}/images")
public class ProductImagesController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping
    public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable Long productId) {
        return ResponseEntity.ok(productImageService.getImagesByProductId(productId));
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ProductImage> getProductImage(@PathVariable Long productId, @PathVariable Long imageId) {
        // Verificar que la imagen pertenece al producto solicitado
        if (!productImageService.imageExistsForProduct(productId, imageId)) {
            return ResponseEntity.notFound().build();
        }
        
        ProductImage image = productImageService.getProductImageById(imageId);
        return ResponseEntity.ok(image);
    }

    @GetMapping(value = "/{imageId}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getProductImageFile(@PathVariable Long productId, @PathVariable Long imageId) {
        // Verificar que la imagen pertenece al producto solicitado
        if (!productImageService.imageExistsForProduct(productId, imageId)) {
            return ResponseEntity.notFound().build();
        }
        
        ProductImage image = productImageService.getProductImageById(imageId);
        if (image == null || image.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok()
                .header("Content-Type", image.getImageType()) // Esto nos permite servir la imagen con su tipo correcto
                .header("Content-Disposition", "inline; filename=\"" + image.getImageName() + "\"") // Esto sugiere al navegador cómo manejar el archivo, lo musestra en vez de descargarlo
                .body(image.getImageData());
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteProductImage(@PathVariable Long productId, @PathVariable Long imageId) {
        // Verificar que la imagen pertenece al producto solicitado
        if (!productImageService.imageExistsForProduct(productId, imageId)) {
            return ResponseEntity.status(404).body("Imagen no encontrada para el producto");
        }
        
        productImageService.deleteProductImage(imageId);
        return ResponseEntity.ok("Imagen eliminada correctamente");
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(
            @PathVariable Long productId,
            @RequestParam("file") MultipartFile file) {
        try {
            ProductImage image = productImageService.uploadProductImage(productId, file);
            return ResponseEntity.ok(image);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al subir la imagen: " + e.getMessage());
        }
    }
}