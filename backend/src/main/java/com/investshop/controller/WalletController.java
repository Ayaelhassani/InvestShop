package com.investshop.controller;

import com.investshop.dto.WalletDTO;
import com.investshop.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Controller REST exposant les endpoints liés aux wallets
 * Accessible via /api/wallets
 */
@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    //Retourne tous les wallets avec l'api
    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }
}