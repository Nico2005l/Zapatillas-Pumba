package com.uade.tpo.zapatillasPumba.service.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String category, String seller, Boolean isVisible) {
        if (category != null) {
            return productRepository.findByCategoryAndSellerAndIsVisible(category, seller, isVisible);
        }
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct != null) {
            product.setId(productId);
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


}
