package com.uade.tpo.zapatillasPumba.controllers.discounts;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiscountRequest {
    private Long productId;
    private Double value;
    private LocalDate startAt;
    private LocalDate endAt;
}