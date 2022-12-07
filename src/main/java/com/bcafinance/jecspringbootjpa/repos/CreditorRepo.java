package com.bcafinance.jecspringbootjpa.repos;

import com.bcafinance.jecspringbootjpa.models.Creditors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CreditorRepo extends JpaRepository<Creditors,Long> {
    Page<Creditors> findByInstallmentGreaterThanEqual(Double greater, Pageable pageable);
    Page<Creditors> findByApplicationDurationBetween(Integer min, Integer max, Pageable pageable);
    Page<Creditors> findByApplicationDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}