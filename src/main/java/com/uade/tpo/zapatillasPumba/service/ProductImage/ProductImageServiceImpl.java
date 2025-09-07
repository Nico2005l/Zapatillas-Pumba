package com.uade.tpo.zapatillasPumba.service.ProductImage;

import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import com.uade.tpo.zapatillasPumba.repository.ProductImageRepository;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;
import com.uade.tpo.zapatillasPumba.controllers.productImages.ProductImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductImage> getImagesByProductId(Long productId) {
        return productImageRepository.findByProductId(productId);
    }

    @Override
    public ProductImage getProductImageById(Long imageId) {
        return productImageRepository.findById(imageId).orElse(null);
    }

    @Override
    public void deleteProductImage(Long imageId) {
        productImageRepository.deleteById(imageId);
    }

    @Override
    public ProductImage uploadProductImage(Long productId, MultipartFile file) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        try {
            ProductImage image = new ProductImage();
            image.setProduct(product);
            image.setImageData(file.getBytes());
            image.setImageType(file.getContentType());
            image.setImageName(file.getOriginalFilename());
            return productImageRepository.save(image);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
        }
    }

    @Override
    public boolean imageExistsForProduct(Long productId, Long imageId) {
        Optional<ProductImage> image = productImageRepository.findById(imageId);
        return image.isPresent() && 
               image.get().getProduct() != null && 
               image.get().getProduct().getId().equals(productId);
    }
}