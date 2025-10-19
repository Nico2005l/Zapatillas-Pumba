package com.uade.tpo.zapatillasPumba.controllers.products;

import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.service.Product.ProductService;
import com.uade.tpo.zapatillasPumba.controllers.common.DeleteResponse;
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

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Boolean isVisible) {
        return ResponseEntity.ok(productService.getProducts(category, isVisible));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        URI location = ServletUriComponentsBuilder // Es otra forma de hacer las URIs
            .fromCurrentRequest()          // Toma la URL actual (/products)
            .path("/{id}")                 // Añade "/{id}" al final
            .buildAndExpand(product.getId()) // Reemplaza {id} con el ID real del producto
            .toUri();                      // Convierte todo a un objeto URI
        return ResponseEntity.created(location).body(product);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }


    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequest productRequest) {
        Product updated = productService.updateProduct(productId, productRequest);
        return ResponseEntity.ok(updated);
    }

  
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}
