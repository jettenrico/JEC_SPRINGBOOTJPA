package com.bcafinance.jecspringbootjpa.repos;

import com.bcafinance.jecspringbootjpa.models.Wallets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallets,Long> {
    Page<Wallets> findByFirstNameContaining(String name, Pageable pageable);
}