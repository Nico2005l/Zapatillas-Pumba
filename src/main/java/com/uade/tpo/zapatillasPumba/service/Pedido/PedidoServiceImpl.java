package com.uade.tpo.zapatillasPumba.service.Pedido;

import com.uade.tpo.zapatillasPumba.entity.*;
import com.uade.tpo.zapatillasPumba.exceptions.PedidoNotFoundException;
import com.uade.tpo.zapatillasPumba.repository.PedidoRepository;
import com.uade.tpo.zapatillasPumba.service.Cart.CartService;
import com.uade.tpo.zapatillasPumba.service.CartItem.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CartItemService cartItemService;

    @Override
    @Transactional
    public Pedido createPedidoFromCart(Long userId) {
        Cart cart = cartService.getUserCart(userId);
        if (cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Cannot create pedido from empty cart");
        }

        Pedido pedido = new Pedido();
        pedido.setUser(cart.getUser());
        pedido.setCreatedAt(LocalDateTime.now());

        // Transfer cart items to pedido items
        for (CartItem cartItem : cart.getCartItems()) {
            PedidoItem pedidoItem = new PedidoItem();
            pedidoItem.setPedido(pedido);
            pedidoItem.setProduct(cartItem.getProduct());
            pedidoItem.setQuantity(cartItem.getQuantity());
            pedidoItem.setUnitPrice(cartItem.getProduct().getPrice());
            pedidoItem.setDiscountApplied(cartItem.getDiscountApplied());
            pedidoItem.setSubtotal(cartItemService.getSubtotal(cartItem));
            pedido.getItems().add(pedidoItem);
        }

        double total = pedido.getItems().stream()
            .mapToDouble(PedidoItem::getSubtotal)
            .sum();
        pedido.setTotalAmount(total);

        Pedido savedPedido = pedidoRepository.save(pedido);
        
        // Clear the cart after creating pedido
        cartService.deleteCart(cart.getId());
        
        return savedPedido;
    }

    @Override
    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new PedidoNotFoundException("Pedido not found with id: " + id));
    }

    @Override
    public List<Pedido> getUserPedidos(Long userId) {
        return pedidoRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Pedido updatePedidoStatus(Long id, String status) {
        Pedido pedido = getPedidoById(id);
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido getUserPedido(Long userId, Long pedidoId) {
        Pedido pedido = getPedidoById(pedidoId);
        if (!pedido.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("User does not have access to this pedido");
        }
        return pedido;
    }
}