package com.investshop.repository;

import com.investshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    //Verifie si un wallet est deja dans le panier
    boolean existsByWalletId(Long walletId);

    //Supprime un item du panier par l'id du wallet
    void deleteByWalletId(Long walletId);
}