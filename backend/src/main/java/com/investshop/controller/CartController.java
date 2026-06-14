package com.investshop.controller;

import com.investshop.dto.CartDTO;
import com.investshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

/**
 * Controller REST exposant les endpoints liés au panier
 * Accessible via /api/cart
 */

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    //Retourne le contenu du panier
    @GetMapping
    public ResponseEntity<CartDTO> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    //Ajoute un wallet au panier
    @PostMapping("/add/{walletId}")
    public ResponseEntity<Void> addToCart(@PathVariable Long walletId) {
        cartService.addToCart(walletId);
        return ResponseEntity.ok().build();
    }

    //Supprime le wallet du panier
    @DeleteMapping("/remove/{walletId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long walletId) {
        cartService.removeFromCart(walletId);
        return ResponseEntity.ok().build();
    }

    //Sauvegarde le panier en JSON
    @PostMapping("/save")
    public ResponseEntity<Void> saveCart() throws IOException {
        cartService.saveCart();
        return ResponseEntity.ok().build();
    }
}