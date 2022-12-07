package com.bcafinance.jecspringbootjpa.models;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 12/5/2022
@Last Modified 12/5/2022 1:19 PM
Version 1.0
*/

@Entity
@Table(name = "MstCreditors")
public class Creditors implements Serializable {

    private static final long serialversionUID = 1L;


    @Id
    @Column(name = "CreditorsID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "accountNumber", nullable = false, unique = true)
    private Double accountNumber;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "ApplicationDuration" , nullable = false)
    private Integer applicationDuration;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "Installment", nullable = false)
    private Double installment;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "Administration", nullable = false)
    private Double administration;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "ApplicationDate",nullable = false)
    private LocalDate applicationDate;

//    @NotEmpty(message = ConstantMessage.ERROR_DATA_INVALID)
    @Column(name = "EndedDate",nullable = false)
    private LocalDate endedDate;
    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Double accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getApplicationDuration() {
        return applicationDuration;
    }

    public void setApplicationDuration(Integer applicationDuration) {
        this.applicationDuration = applicationDuration;
    }

    public Double getInstallment() {
        return installment;
    }

    public void setInstallment(Double installment) {
        this.installment = installment;
    }

    public Double getAdministration() {
        return administration;
    }

    public void setAdministration(Double administration) {
        this.administration = administration;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(LocalDate endedDate) {
        this.endedDate = endedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}