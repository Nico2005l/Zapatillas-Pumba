package com.uade.tpo.zapatillasPumba.service.Discount;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.entity.Product;
import com.uade.tpo.zapatillasPumba.exceptions.DiscountOutOfRangeException;
import com.uade.tpo.zapatillasPumba.exceptions.DiscountProductNotFoundException;
import com.uade.tpo.zapatillasPumba.controllers.discounts.DiscountRequest;
import com.uade.tpo.zapatillasPumba.repository.DiscountRepository;
import com.uade.tpo.zapatillasPumba.repository.ProductRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Discount getDiscountById(Long id) {
        return discountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    @Transactional
    public Discount updateDiscount(Long id, DiscountRequest request) throws DiscountProductNotFoundException {
        Optional<Discount> opt = discountRepository.findById(id);
        if (opt.isEmpty()) return null;

        Discount discount = opt.get();
        if (request.getValue() < 0 || request.getValue() > 1.00) {
            throw new DiscountOutOfRangeException();
        } else {
            discount.setValue(request.getValue());
            discount.setStartAt(request.getStartAt());
            discount.setEndAt(request.getEndAt());
            discount.setIsActive(true);
            discount.setValue(request.getValue());
        }

        // Si viene productId en el request y querés actualizar la asociación:
        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            discount.setProduct(product);
        }

        return discountRepository.save(discount);
    }

    @Override
    @Transactional
    public Discount createDiscount(DiscountRequest request) throws DiscountProductNotFoundException {
        Discount discount = new Discount();
        if (request.getValue() < 0 || request.getValue() > 1.00) {
            throw new DiscountOutOfRangeException();
        } else {
            discount.setValue(request.getValue());
            discount.setStartAt(request.getStartAt());
            discount.setEndAt(request.getEndAt());
            discount.setIsActive(true);
            discount.setValue(request.getValue());
        }

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new DiscountProductNotFoundException("Product not found"));
            discount.setProduct(product);
        }
        return discountRepository.save(discount);
    }

    @Override
    @Transactional
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }



    @Transactional
    public Discount cloneDiscountToProduct(Long discountId, Long productId) throws DiscountProductNotFoundException {
        Discount original = discountRepository.findById(discountId)
                .orElseThrow(() -> new DiscountProductNotFoundException("Discount not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new DiscountProductNotFoundException("Product not found"));

        Discount copy = new Discount();
        copy.setValue(original.getValue());
        copy.setStartAt(original.getStartAt());
        copy.setEndAt(original.getEndAt());
        copy.setIsActive(original.getIsActive());
        copy.setProduct(product);

        return discountRepository.save(copy);
    }


    public List<Discount> getDiscountsForProduct(Long productId) {
        return discountRepository.findByProductId(productId);
    }
}