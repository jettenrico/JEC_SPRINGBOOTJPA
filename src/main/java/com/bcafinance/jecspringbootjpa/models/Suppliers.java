package com.bcafinance.jecspringbootjpa.models;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 1:50 PM
Version 1.0
*/

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MstSuppliers")
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID")
    private Long id;

    @Column(name = "CompanyName",length = 50,nullable = false,unique = true)
    private String companyName;

    @Column(name = "SupervisorName",length = 70,nullable = false)
    private String supervisorName;

    @Column(name = "CompanyAddress", nullable = false)
    private String address;

    @Column(name = "CompanyEmail",length = 50 ,nullable = false,unique = true)
    private String email;

    @Column(name = "CompanyPhoneNumber", length = 16, nullable = false, unique = true)
    private String phoneNumber;

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

    public Suppliers() {
    }
}