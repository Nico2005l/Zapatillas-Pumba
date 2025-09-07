package com.uade.tpo.zapatillasPumba.service.Discount;

import java.util.List;

import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.exceptions.DiscountProductNotFoundException;
import com.uade.tpo.zapatillasPumba.controllers.discounts.DiscountRequest;

public interface DiscountService {

    Discount getDiscountById(Long id);
    List<Discount> getAllDiscounts();
    Discount createDiscount(DiscountRequest request) throws DiscountProductNotFoundException;
    Discount updateDiscount(Long id, DiscountRequest request) throws DiscountProductNotFoundException;
    void deleteDiscount(Long id);
    Discount cloneDiscountToProduct(Long discountId, Long productId) throws DiscountProductNotFoundException;
    List<Discount> getDiscountsForProduct(Long productId);
}