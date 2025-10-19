package com.uade.tpo.zapatillasPumba.controllers.pedidos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponse {
    private Long id;
    private String pedidoNumber;
    private String status;
    private LocalDateTime createdAt;
    private List<PedidoItemResponse> items;
    private Double totalAmount;
}