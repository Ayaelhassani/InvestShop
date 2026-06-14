package com.investshop.service;

import com.investshop.entity.CartItem;
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
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private WalletMapper walletMapper;

    @InjectMocks
    private CartService cartService;

    @Test
    void addToCart_shouldAddWalletSuccessfully() {
        Wallet wallet = Wallet.builder()
                .id(1L)
                .name("Conservative Wallet")
                .assetGroup("Treasury Bonds + CDs")
                .gainsLastYear(9.50)
                .price(500.00)
                .build();

        when(cartItemRepository.existsByWalletId(1L)).thenReturn(false);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        cartService.addToCart(1L);
        //Verifie que sle save a ete appele une fois
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void addToCart_shouldThrow_whenWalletAlreadyInCart() {
        when(cartItemRepository.existsByWalletId(1L)).thenReturn(true);
        //Verifie que l'exception est bien lanee
        assertThatThrownBy(() -> cartService.addToCart(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Ce wallet est déjà dans le panier");
    }

    @Test
    void addToCart_shouldThrow_whenWalletNotFound() {
        when(cartItemRepository.existsByWalletId(1L)).thenReturn(false);
        when(walletRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> cartService.addToCart(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Wallet introuvable avec l'id : 1");
    }

    @Test
    void getCart_shouldReturnEmptyCart() {
        when(cartItemRepository.findAll()).thenReturn(List.of());
        var result = cartService.getCart();

        assertThat(result.getItems()).isEmpty();
        assertThat(result.getTotal()).isEqualTo(0.0);
    }

    @Test
    void removeFromCart_shouldThrow_whenWalletNotInCart() {
        when(cartItemRepository.existsByWalletId(1L)).thenReturn(false);
        assertThatThrownBy(() -> cartService.removeFromCart(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Ce wallet n'est pas dans le panier");
    }
}