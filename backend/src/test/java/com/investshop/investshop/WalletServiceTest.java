package com.investshop.service;

import com.investshop.dto.WalletDTO;
import com.investshop.entity.Wallet;
import com.investshop.mapper.WalletMapper;
import com.investshop.repository.CartItemRepository;
import com.investshop.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private WalletMapper walletMapper;

    @InjectMocks
    private WalletService walletService;

    @Test
    void getAllWallets_shouldReturnListOfWallets() {
        //Preparation des donnees de test
        Wallet wallet = Wallet.builder()
                .id(1L)
                .name("Conservative Wallet")
                .assetGroup("Treasury Bonds + CDs")
                .gainsLastYear(9.50)
                .price(500.00)
                .build();

        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(1L);
        walletDTO.setName("Conservative Wallet");
        when(walletRepository.findAll()).thenReturn(List.of(wallet));
        when(walletMapper.toDTO(wallet)).thenReturn(walletDTO);
        when(cartItemRepository.existsByWalletId(1L)).thenReturn(false);

        //Appzl du szevice
        List<WalletDTO> result = walletService.getAllWallets();
        //Verification du resultat
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Conservative Wallet");
        assertThat(result.get(0).getInCart()).isFalse();
    }

    @Test
    void getAllWallets_shouldMarkWalletAsInCart() {
        Wallet wallet = Wallet.builder()
                .id(1L)
                .name("Conservative Wallet")
                .assetGroup("Treasury Bonds + CDs")
                .gainsLastYear(9.50)
                .price(500.00)
                .build();

        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(1L);
        walletDTO.setName("Conservative Wallet");

        when(walletRepository.findAll()).thenReturn(List.of(wallet));
        when(walletMapper.toDTO(wallet)).thenReturn(walletDTO);
        // on simule que le wallet est déjà dans le panier
        when(cartItemRepository.existsByWalletId(1L)).thenReturn(true);

        List<WalletDTO> result = walletService.getAllWallets();

        assertThat(result.get(0).getInCart()).isTrue();
    }
}