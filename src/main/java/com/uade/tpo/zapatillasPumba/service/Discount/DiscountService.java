package com.uade.tpo.zapatillasPumba.service.Discount;

import java.util.List;
import com.uade.tpo.zapatillasPumba.entity.Discount;
import com.uade.tpo.zapatillasPumba.controllers.discounts.DiscountRequest;

public interface DiscountService {
    Discount getDiscountById(Long id);
    List<Discount> getAllDiscounts();
    Discount createDiscount(Discount request);
    Discount updateDiscount(Long id, DiscountRequest request);
    void deleteDiscount(Long id);
}