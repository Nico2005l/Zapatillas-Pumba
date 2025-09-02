package com.uade.tpo.zapatillasPumba.service.Product;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.Product;

public interface ProductService {

    public List<Product> getProducts(String category, String seller, Boolean isVisible);

    public Product createProduct(Product product);

    public Product updateProduct(Long productId, Product product);

    public Product getProductById(Long productId);

    public void deleteProduct(Long productId);

}
