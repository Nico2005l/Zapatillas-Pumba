package com.uade.tpo.zapatillasPumba.controllers.products;

import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * GET /products : List all products with optional filtering
     * @param category Filter by category ID
     * @param isVisible Filter by visibility
     * @return A list of products matching the criteria
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Boolean isVisible) {
        return ResponseEntity.ok(productService.getProducts(category, isVisible));
    }

    /**
     * POST /products : Create a new product
     * @param productRequest Product data
     * @return The created product with 201 Created status
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    /**
     * GET /products/{productId} : Get a product by ID
     * @param productId The product ID
     * @return The product if found
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    /**
     * PUT /products/{productId} : Update a product
     * @param productId The product ID
     * @param productRequest Updated product data
     * @return The updated product
     */
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequest productRequest) {
        Product updated = productService.updateProduct(productId, productRequest);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /products/{productId} : Delete a product
     * @param productId The product ID
     * @return 204 No Content on success
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
