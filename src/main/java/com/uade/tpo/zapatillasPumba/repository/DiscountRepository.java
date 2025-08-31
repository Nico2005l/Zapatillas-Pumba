package com.uade.tpo.zapatillasPumba.repository;

public class DiscountRepository extends JpaRepository<Category, Long>{

    public void applyDiscount(Order order, Discount discount) {
        // Logic to apply discount to the order
    }

}
