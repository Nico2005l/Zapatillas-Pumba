package com.uade.tpo.zapatillasPumba.service.Discount;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.entity.Product;
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
        Optional<Discount> discount = discountRepository.findById(id);
        return discount.orElse(null);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount updateDiscount(Long id, DiscountRequest request) {
        Discount discount = discountRepository.findById(id).orElse(null);
        if (discount != null) {
            discount.setType(request.getType());
            discount.setValue(request.getValue());
            discount.setStartAt(request.getStartAt());
            discount.setEndAt(request.getEndAt());
            discount.setIsActive(request.getIsActive());
           
            return discountRepository.save(discount);
        }
        return null;
    }

    @Override
    public Discount createDiscount(DiscountRequest request) throws DiscountProductNotFoundException {
        Discount discount = new Discount();
        Product product = productRepository.findById(request.getProductId()).orElse(null);

        if (product != null) {
                discount.setProduct(product);
                discount.setType(request.getType());
                discount.setValue(request.getValue());
                discount.setStartAt(request.getStartAt());
                discount.setEndAt(request.getEndAt());
                discount.setIsActive(request.getIsActive());
                return discountRepository.save(discount);
        } else{
            throw new DiscountProductNotFoundException();
        }
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }

    public boolean applyDiscountToProduct(Long discountId, Long productId) {
    Optional<Discount> discountOpt = discountRepository.findById(discountId);
    Optional<Product> productOpt = productRepository.findById(productId);

    if (discountOpt.isPresent() && productOpt.isPresent()) {
        Product product = productOpt.get();
        product.setDiscount(discountOpt.get()); // Asume que Product tiene un campo Discount
        productRepository.save(product);
        return true;
    }
    return false;
}
}
