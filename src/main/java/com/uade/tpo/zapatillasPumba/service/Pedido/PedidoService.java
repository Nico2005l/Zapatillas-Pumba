package com.uade.tpo.zapatillasPumba.service.Pedido;

import com.uade.tpo.zapatillasPumba.entity.Pedido;
import com.uade.tpo.zapatillasPumba.entity.PedidoStatus;
import java.util.List;

public interface PedidoService {
    Pedido createPedidoFromCart(Long userId);
    Pedido getPedidoById(Long id);
    Pedido getPedidoByNumber(String pedidoNumber);
    List<Pedido> getUserPedidos(Long userId);
    Pedido updatePedidoStatus(Long id, PedidoStatus status);
    Pedido getUserPedido(Long userId, Long pedidoId);
}