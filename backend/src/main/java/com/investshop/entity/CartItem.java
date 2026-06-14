package com.investshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Appartion du wallet une seule fois
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id", unique = true, nullable = false)
    private Wallet wallet;
}