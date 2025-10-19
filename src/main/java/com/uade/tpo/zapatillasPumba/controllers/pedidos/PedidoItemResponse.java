package com.uade.tpo.zapatillasPumba.controllers.pedidos;

import lombok.Data;

@Data
public class PedidoItemResponse {
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private Double unitPrice;
    private Double discountApplied;
    private Double subtotal;
}