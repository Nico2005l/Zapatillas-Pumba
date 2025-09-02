package com.uade.tpo.zapatillasPumba.service;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.Product;

public interface ProductService {

    void createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void updateProduct(Long id, Product product);
    void deleteProduct(Long id);

}
