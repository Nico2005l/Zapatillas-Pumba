package com.uade.tpo.zapatillasPumba.mapper;

import com.uade.tpo.zapatillasPumba.entity.Pedido;
import com.uade.tpo.zapatillasPumba.entity.PedidoItem;
import com.uade.tpo.zapatillasPumba.controllers.pedidos.PedidoResponse;
import com.uade.tpo.zapatillasPumba.controllers.pedidos.PedidoItemResponse;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {
    public PedidoResponse toResponse(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();
        response.setId(pedido.getId());
        response.setPedidoNumber(pedido.getPedidoNumber());
        response.setStatus(pedido.getStatus().getDisplayName());
        response.setCreatedAt(pedido.getCreatedAt());
        response.setTotalAmount(pedido.getTotalAmount());
        response.setItems(pedido.getItems().stream()
                .map(this::toItemResponse)
                .collect(Collectors.toList()));
        return response;
    }

    private PedidoItemResponse toItemResponse(PedidoItem item) {
        PedidoItemResponse response = new PedidoItemResponse();
        response.setProductId(item.getProduct().getId());
        response.setProductTitle(item.getProduct().getTitle());
        response.setQuantity(item.getQuantity());
        response.setUnitPrice(item.getUnitPrice());
        response.setDiscountApplied(item.getDiscountApplied());
        response.setSubtotal(item.getSubtotal());
        return response;
    }
}