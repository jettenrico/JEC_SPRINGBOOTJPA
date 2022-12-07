package com.bcafinance.jecspringbootjpa.repos;

import com.bcafinance.jecspringbootjpa.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Accounts,Long> {
    Accounts findByAccountNumber(String rekening);
}