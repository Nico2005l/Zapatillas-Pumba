package com.uade.tpo.zapatillasPumba.service.Discount;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.controllers.discounts.DiscountRequest;
import com.uade.tpo.zapatillasPumba.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

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
    public void updateDiscount(Long id, DiscountRequest request) {
        Discount discount = discountRepository.findById(id).orElse(null);
        if (discount != null) {
            discount.setType(request.getType());
            discount.setValue(request.getValue());
            discount.setStartAt(request.getStartAt());
            discount.setEndAt(request.getEndAt());
            discount.setIsActive(request.getIsActive());
            // Si necesitas actualizar el producto, deberías buscarlo y setearlo aquí
            discountRepository.save(discount);
        }
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
