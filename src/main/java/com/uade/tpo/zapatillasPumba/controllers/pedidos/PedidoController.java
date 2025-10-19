package com.uade.tpo.zapatillasPumba.controllers.pedidos;

import com.uade.tpo.zapatillasPumba.entity.Pedido;
import com.uade.tpo.zapatillasPumba.entity.PedidoStatus;
import com.uade.tpo.zapatillasPumba.service.Pedido.PedidoService;
import com.uade.tpo.zapatillasPumba.mapper.PedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoMapper pedidoMapper;

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<PedidoResponse> createPedido(@PathVariable Long userId) {
        Pedido pedido = pedidoService.createPedidoFromCart(userId);
        return ResponseEntity.created(URI.create("/pedidos/" + pedido.getId()))
                        .body(pedidoMapper.toResponse(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> getPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoMapper.toResponse(pedidoService.getPedidoById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PedidoResponse>> getUserPedidos(@PathVariable Long userId) {
        return ResponseEntity.ok(pedidoService.getUserPedidos(userId).stream()
                .map(pedidoMapper::toResponse)
                .collect(Collectors.toList()));
    }
}