package com.uade.tpo.zapatillasPumba.repository;

import com.uade.tpo.zapatillasPumba.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = :cartId")
    void deleteAllCartItemsByCartId(Long cartId);
        
    List<CartItem> findByCartId(Long cartId);
}