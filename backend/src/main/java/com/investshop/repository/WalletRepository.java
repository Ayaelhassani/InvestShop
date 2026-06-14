package com.investshop.repository;

import com.investshop.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    // JpaRepository nous donne déjà findAll, findById, save, delete etc
}