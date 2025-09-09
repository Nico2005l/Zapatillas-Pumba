package com.uade.tpo.zapatillasPumba.service.Product;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.controllers.products.ProductRequest;
import com.uade.tpo.zapatillasPumba.exceptions.InvalidProductDataException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductAlreadyExistsException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductNotFoundException;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;
import com.uade.tpo.zapatillasPumba.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryService categoryService;

    public List<Product> getProducts(Long categoryId, Boolean isVisible) {
        List<Product> products = productRepository.findAll();
        if (isVisible != null) {
            products = products.stream().filter(p -> p.getIsVisible().equals(isVisible)).toList();
        }
        return products;
    }

    public Product createProduct(ProductRequest productRequest) {
        validateProductData(productRequest);
        
        if (productRepository.findByTitle(productRequest.getTitle()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        
        Product product = new Product();
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setIsVisible(productRequest.getIsVisible());
        product.setCreatedAt(java.time.LocalDate.now());


        if (productRequest.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productRequest.getCategoryId());
            product.setCategory(category);
        }
        
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    public Product updateProduct(Long productId, ProductRequest productRequest) {
        validateProductData(productRequest);
        
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        
        Optional<Product> existingProduct = productRepository.findByTitle(productRequest.getTitle());
        if (existingProduct.isPresent() && !existingProduct.get().getId().equals(productId)) {
            throw new ProductAlreadyExistsException();
        }
        
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setIsVisible(productRequest.getIsVisible());
        
        if (productRequest.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productRequest.getCategoryId());
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }
        
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(productId);
    }
    
    private void validateProductData(ProductRequest request) { // Este metodo valida los datos del producto para que no sean invalidos
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new InvalidProductDataException("El título del producto no puede estar vacío");
        }
        
        if (request.getPrice() == null || request.getPrice() <= 0) {
            throw new InvalidProductDataException("El precio del producto debe ser mayor a cero");
        }
        
        if (request.getStock() == null || request.getStock() < 0) {
            throw new InvalidProductDataException("El stock del producto no puede ser negativo");
        }
    }
}
