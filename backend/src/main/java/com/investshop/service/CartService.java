package com.investshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.investshop.dto.CartDTO;
import com.investshop.dto.WalletDTO;
import com.investshop.entity.CartItem;
import com.investshop.mapper.WalletMapper;
import com.investshop.repository.CartItemRepository;
import com.investshop.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    //recuperation du chemin du fichier depuis application.properties
    @Value("${app.cart.file-path}")
    private String cartFilePath;

    public CartDTO getCart() {
        List<WalletDTO> items = cartItemRepository.findAll()
                .stream()
                .map(cartItem -> walletMapper.toDTO(cartItem.getWallet()))
                .toList();

        double total = items.stream()
                .mapToDouble(WalletDTO::getPrice)
                .sum();

        return CartDTO.builder()
                .items(items)
                .total(total)
                .build();
    }

    public void addToCart(Long walletId) {
        // vérifie que le wallet n'est pas déjà dans le panier
        if (cartItemRepository.existsByWalletId(walletId)) {
            throw new IllegalStateException("Ce wallet est déjà dans le panier");
        }

        var wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet introuvable avec l'id : " + walletId));

        cartItemRepository.save(CartItem.builder().wallet(wallet).build());
    }

    @Transactional
    public void removeFromCart(Long walletId) {
        if (!cartItemRepository.existsByWalletId(walletId)) {
            throw new RuntimeException("Ce wallet n'est pas dans le panier");
        }
        cartItemRepository.deleteByWalletId(walletId);
    }

    public void saveCart() throws IOException {
        CartDTO cart = getCart();

        File file = new File(cartFilePath);
        //creation du dossier s'il n'existe pas
        file.getParentFile().mkdirs();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, cart);
    }
}