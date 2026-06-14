package com.investshop.controller;

import com.investshop.dto.WalletDTO;
import com.investshop.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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