package com.bcafinance.jecspringbootjpa.dto;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 1:50 PM
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bcafinance.jecspringbootjpa.models.Warehouses;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WarehousesDTO {

    private Long id;

    @Length(message = ConstantMessage.WARNING_WAREHOUSE_ADDRESS_MAX_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_BLANK_ADDRESS)
    private String address;

    @Length(message = ConstantMessage.WARNING_WAREHOUSE_SUPERVISOR_MAX_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_BLANK_SUPERVISOR)
    private String warehouseSpv;

    private String warehouseDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWarehouseSpv() {
        return warehouseSpv;
    }

    public void setWarehouseSpv(String warehouseSpv) {
        this.warehouseSpv = warehouseSpv;
    }

    public String getWarehouseDesc() {
        return warehouseDesc;
    }

    public void setWarehouseDesc(String warehouseDesc) {
        this.warehouseDesc = warehouseDesc;
    }
}