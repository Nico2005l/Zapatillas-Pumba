package com.uade.tpo.zapatillasPumba.service.Product;

import com.uade.tpo.zapatillasPumba.entity.Category;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.controllers.products.ProductRequest;
import com.uade.tpo.zapatillasPumba.exceptions.CategoryNotFoundException;
import com.uade.tpo.zapatillasPumba.exceptions.InvalidProductDataException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductAlreadyExistsException;
import com.uade.tpo.zapatillasPumba.exceptions.ProductNotFoundException;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private com.uade.tpo.zapatillasPumba.repository.CategoryRepository categoryRepository;

    @Override
    public List<Product> getProducts(Long categoryId, Long sellerId, Boolean isVisible) {
        List<Product> products = productRepository.findAll();
        if (categoryId != null) {
            products = products.stream().filter(p -> p.getCategory() != null && p.getCategory().getId().equals(categoryId)).toList();
        }
        if (sellerId != null) {
            products = products.stream().filter(p -> p.getSeller() != null && p.getSeller().getId().equals(sellerId)).toList();
        }
        if (isVisible != null) {
            products = products.stream().filter(p -> p.getIsVisible().equals(isVisible)).toList();
        }
        return products;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        // Validar datos del producto
        validateProductData(productRequest);
        
        // Verificar si existe un producto con el mismo título
        if (productRepository.findByTitle(productRequest.getTitle()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        
        Product product = new Product();
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setIsVisible(productRequest.getIsVisible());
        
        // Asignar categoría si existe
        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(CategoryNotFoundException::new);
            product.setCategory(category);
        }
        
        // TODO: Asignar seller y productImages si corresponde
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product updateProduct(Long productId, ProductRequest productRequest) {
        // Validar datos del producto
        validateProductData(productRequest);
        
        // Buscar el producto
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        
        // Verificar si el título nuevo ya existe en otro producto
        Optional<Product> existingProduct = productRepository.findByTitle(productRequest.getTitle());
        if (existingProduct.isPresent() && !existingProduct.get().getId().equals(productId)) {
            throw new ProductAlreadyExistsException();
        }
        
        // Actualizar los datos
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setIsVisible(productRequest.getIsVisible());
        
        // Actualizar categoría
        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(CategoryNotFoundException::new);
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }
        
        // TODO: Actualizar seller y productImages si corresponde
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(productId);
    }
    
    /**
     * Valida los datos del producto
     * @param request El request con los datos del producto
     * @throws InvalidProductDataException si algún dato es inválido
     */
    private void validateProductData(ProductRequest request) {
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
