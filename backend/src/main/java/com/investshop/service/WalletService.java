package com.investshop.service;

import com.investshop.dto.WalletDTO;
import com.investshop.mapper.WalletMapper;
import com.investshop.repository.CartItemRepository;
import com.investshop.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final CartItemRepository cartItemRepository;
    private final WalletMapper walletMapper;

    public List<WalletDTO> getAllWallets() {
        return walletRepository.findAll().stream()
                .map(wallet -> {
                    WalletDTO dto = walletMapper.toDTO(wallet);
                    //Verification si le wallet est deja dans le panier
                    dto.setInCart(cartItemRepository.existsByWalletId(wallet.getId()));
                    return dto;
                })
                .toList();
    }
}