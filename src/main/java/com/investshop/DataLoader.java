package com.investshop;

import com.investshop.entity.Wallet;
import com.investshop.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final WalletRepository walletRepository;

    @Override
    public void run(String... args) {
        //Insertion des donnees seulement si la table est vide
        if (walletRepository.count() == 0) {
            walletRepository.saveAll(List.of(
                Wallet.builder().name("Conservative Wallet").assetGroup("Treasury Bonds + CDs").gainsLastYear(9.50).price(500.00).build(),
                Wallet.builder().name("Moderate Wallet").assetGroup("Fixed Income + REITs").gainsLastYear(12.30).price(1000.00).build(),
                Wallet.builder().name("Aggressive Wallet").assetGroup("Stocks + REITs + BDRs").gainsLastYear(18.75).price(2000.00).build(),
                Wallet.builder().name("Global Growth Wallet").assetGroup("International ETFs + Stocks").gainsLastYear(22.10).price(3000.00).build()
            ));
            System.out.println(" Données initiales insérées !");
        }
    }
}