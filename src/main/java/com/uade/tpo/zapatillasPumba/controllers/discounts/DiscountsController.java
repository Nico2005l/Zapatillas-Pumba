package com.uade.tpo.zapatillasPumba.controllers.discounts;

import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.exceptions.DiscountProductNotFoundException;
import com.uade.tpo.zapatillasPumba.service.Discount.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable Long id) {
        Discount discount = discountService.getDiscountById(id);
        return discount != null ? ResponseEntity.ok(discount) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody DiscountRequest request) {
        try {
            Discount created = discountService.createDiscount(request);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(created.getId())
                    .toUri();
            return ResponseEntity.created(location).body(created);
        } catch (DiscountProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable Long id, @RequestBody DiscountRequest request) {
        try {
            Discount updated = discountService.updateDiscount(id, request);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (DiscountProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiscount(@PathVariable Long id) {
        String mensaje = discountService.deleteDiscount(id);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/{id}/clone/{productId}")
    public ResponseEntity<Discount> cloneDiscountToProduct(@PathVariable Long id, @PathVariable Long productId) {
        try {
            Discount copy = discountService.cloneDiscountToProduct(id, productId);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .replacePath("/discounts/{id}")
                    .buildAndExpand(copy.getId())
                    .toUri();
            return ResponseEntity.created(location).body(copy);
        } catch (DiscountProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Discount>> getDiscountsForProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(discountService.getDiscountsForProduct(productId));
    }
}
