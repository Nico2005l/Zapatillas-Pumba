package com.uade.tpo.zapatillasPumba.service;

import java.util.List;

public interface DiscountService {

    
    double getDiscountById(Long id);
    List<Double> getAllDiscounts();
    void updateDiscount(Long id, double discount);
    void deleteDiscount(Long id);

}
