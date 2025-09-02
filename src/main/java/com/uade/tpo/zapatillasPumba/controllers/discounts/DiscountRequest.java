package com.uade.tpo.zapatillasPumba.controllers.discounts;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DiscountRequest {
    private Long productId;
    private String type;
    private Double value;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Boolean isActive;
}