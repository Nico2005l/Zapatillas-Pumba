package com.uade.tpo.zapatillasPumba.service.Product;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.controllers.products.ProductRequest;
import com.uade.tpo.zapatillasPumba.exceptions.InvalidProductDataException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductAlreadyExistsException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductDuplicateException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductNotFoundException;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;
import com.uade.tpo.zapatillasPumba.service.Category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(Long categoryId, Boolean isVisible) {
        List<Product> products = productRepository.findAll();
        if (isVisible != null) {
            products = products.stream().filter(p -> p.getIsVisible().equals(isVisible)).toList();
        }
        return products;
    }

    public Product createProduct(ProductRequest productRequest) {
        validateProductData(productRequest);
        
        log.debug("Creating product for category ID: {}", productRequest.getCategoryId());
        
        // Get the category and validate it exists
        Category category = categoryService.getCategoryById(productRequest.getCategoryId());
        if (category.getTipo() == null) {
            throw new InvalidProductDataException("Los productos solo pueden asociarse a categorías de tipo, no de género");
        }

        Product product = new Product();
        product.setTitle(productRequest.getTitle());
        product.setDescripcionCorta(productRequest.getDescripcionCorta());
        product.setDescripcionLarga(productRequest.getDescripcionLarga());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setIsVisible(productRequest.getIsVisible());
        product.setTalle(productRequest.getTalle());
        product.setColor(productRequest.getColor());
        product.setCreatedAt(java.time.LocalDate.now());
        
        // Set up bidirectional relationship
        product.setCategory(category);
        
        // Initialize products list if needed
        if (category.getProducts() == null) {
            category.setProducts(new ArrayList<>());
        }
        category.getProducts().add(product);

        log.debug("Saving product with category: {}", category.getId());
        Product savedProduct = productRepository.save(product);
        log.debug("Saved product. Category has {} products", category.getProducts().size());
        
        return savedProduct;
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public Product updateProduct(Long id, ProductRequest request) {
        Product existingProduct = getProductById(id);

        // Check for duplicates only if title is changing
        if (!existingProduct.getTitle().equalsIgnoreCase(request.getTitle())) {
            if (productRepository.existsByTitleIgnoreCaseAndIdNot(request.getTitle().trim(), id)) {
                throw new ProductDuplicateException(
                    String.format("Ya existe un producto con el título '%s'", request.getTitle())
                );
            }
        }

        // Update fields
        existingProduct.setTitle(request.getTitle());
        existingProduct.setDescripcionCorta(request.getDescripcionCorta());
        existingProduct.setDescripcionLarga(request.getDescripcionLarga());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStock(request.getStock());
        existingProduct.setIsVisible(request.getIsVisible());
        existingProduct.setTalle(request.getTalle());
        existingProduct.setColor(request.getColor());

        return productRepository.save(existingProduct);
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

        if (productRepository.existsByTitleIgnoreCase(request.getTitle().trim())) {
            throw new ProductDuplicateException(
                String.format("Ya existe un producto con el título '%s'", request.getTitle())
            );
        }

        if (request.getDescripcionCorta() == null || request.getDescripcionCorta().trim().isEmpty()) {
            throw new InvalidProductDataException("La descripción corta no puede estar vacía");
        }
        
        if (request.getTalle() == null) {
            throw new InvalidProductDataException("El talle es obligatorio");
        }
        
        if (request.getColor() == null) {
            throw new InvalidProductDataException("El color es obligatorio");
        }
        
        if (request.getPrice() == null || request.getPrice() <= 0) {
            throw new InvalidProductDataException("El precio del producto debe ser mayor a cero");
        }
        
        if (request.getStock() == null || request.getStock() < 0) {
            throw new InvalidProductDataException("El stock del producto no puede ser negativo");
        }
    }
}
