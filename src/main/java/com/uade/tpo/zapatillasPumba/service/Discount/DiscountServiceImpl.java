package com.uade.tpo.zapatillasPumba.service.Discount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscountById(Long id) {
        return discountRepository.findById(id).orElse(null);
    }

    @Override
    public void updateDiscount(Long id, DiscountRequest request) {
        Discount discount = discountRepository.findById(id).orElse(null);
        if (discount != null) {
            discount.setValue(request.getValue());
            discountRepository.save(discount);
        }
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
    
}
