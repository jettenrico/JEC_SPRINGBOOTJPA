package com.bcafinance.jecspringbootjpa.dto;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 1:50 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.models.Warehouses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bcafinance.jecspringbootjpa.models.Suppliers;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SuppliersDTO {

    private Long id;

    private Warehouses warehouses;
    @NotEmpty(message = ConstantMessage.WARNING_BLANK_COMPANY)
    private String companyName;

    @NotEmpty(message = ConstantMessage.WARNING_BLANK_COMPANY)
    private String supervisorName;

    private String address;

    private String email;

    private String phoneNumber;

    private String warehouseAddress;

    private String warehouseSupervisorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Warehouses getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Warehouses warehouses) {
        this.warehouses = warehouses;
    }

    //    public String getWarehouseAddress() {
//        return warehouseAddress;
//    }
//
//    public void setWarehouseAddress(String warehouseAddress) {
//        this.warehouseAddress = warehouseAddress;
//    }
//
//    public String getWarehouseSupervisorName() {
//        return warehouseSupervisorName;
//    }
//
//    public void setWarehouseSupervisorName(String warehouseSupervisorName) {
//        this.warehouseSupervisorName = warehouseSupervisorName;
//    }
}