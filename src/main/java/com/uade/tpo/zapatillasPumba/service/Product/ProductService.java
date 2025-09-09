package com.uade.tpo.zapatillasPumba.service.Product;

import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.controllers.products.ProductRequest;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryNotFoundException;
import com.uade.tpo.zapatillasPumba.exceptions.InvalidProductDataException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductAlreadyExistsException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(Long categoryId, Boolean isVisible);
    
    Product createProduct(ProductRequest productRequest) 
            throws InvalidProductDataException, ProductAlreadyExistsException, CategoryNotFoundException;
    
    Product getProductById(Long productId) 
            throws ProductNotFoundException;
    
    Product updateProduct(Long productId, ProductRequest productRequest) 
            throws ProductNotFoundException, InvalidProductDataException, ProductAlreadyExistsException, CategoryNotFoundException;
    
    void deleteProduct(Long productId) 
            throws ProductNotFoundException;
}