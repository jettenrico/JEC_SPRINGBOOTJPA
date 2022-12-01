package com.bcafinance.jecspringbootjpa.models;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 1:50 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@Table(name = "MstWarehouses")
public class Warehouses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WarehouseID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_BLANK_ADDRESS)
    @Column(name = "WarehouseAddress", nullable = false,unique = true)
    private String address;

    @NotEmpty(message = ConstantMessage.WARNING_BLANK_SUPERVISOR)
    @Column(name = "WarehouseSupervisor",length = 50 ,nullable = false)
    private String warehouseSpv;

    @Column(name = "WarehouseDescription", nullable = true)
    private String warehouseDesc;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    //    @Column(name = "CreatedDate",nullable = true, columnDefinition = "datetime2(7) DEFAULT GETDATE() ")
    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public Warehouses() {
    }
}