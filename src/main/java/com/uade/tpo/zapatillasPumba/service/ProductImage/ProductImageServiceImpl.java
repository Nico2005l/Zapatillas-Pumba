package com.uade.tpo.zapatillasPumba.service.ProductImage;

import com.uade.tpo.zapatillasPumba.entity.ProductImage;
import com.uade.tpo.zapatillasPumba.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImage getProductImageById(Long id) {
        return productImageRepository.findById(id).orElse(null);
    }

    @Override
    public ProductImage createProductImage(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    @Override
    public ProductImage updateProductImage(Long id, ProductImage productImage) {
        productImage.setId(id);
        return productImageRepository.save(productImage);
    }

    @Override
    public void deleteProductImage(Long id) {
        productImageRepository.deleteById(id);
    }
}
