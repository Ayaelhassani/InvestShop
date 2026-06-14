package com.investshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * cette classe représente un portefeuille d'investissement.
 * Contient les informations du wallet comme le nom, le groupe,
 * les gains de l'année dernière et le prix
 */
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "asset_group", nullable = false)
    private String assetGroup;

    @Column(name = "gains_last_year", nullable = false)
    private Double gainsLastYear;

    @Column(nullable = false)
    private Double price;
}