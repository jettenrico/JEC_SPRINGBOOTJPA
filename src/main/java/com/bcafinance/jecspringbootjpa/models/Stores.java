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
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MstStores")
public class Stores implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StoreId")
    private Long id;

    @Length(message = ConstantMessage.WARNING_STORES_MAX_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_BLANK_STORES)
    @Column(name = "StoreName", nullable = false, length = 50)
    private String storeName;

    @Length(message = ConstantMessage.WARNING_WAREHOUSE_ADDRESS_MAX_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_BLANK_ADDRESS_STORE)
    @Column(name = "StoreAddress", nullable = false,unique = true)
    private String addressStore;

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

    @ManyToMany
    @JoinTable(
            name ="StoreWarehouse",
            joinColumns = @JoinColumn(name = "StoreId", referencedColumnName = "StoreId"),
            inverseJoinColumns = @JoinColumn(name = "WarehouseId",referencedColumnName = "WarehouseId")
    )
//    @JsonManagedReference
    private Set<Warehouses> warehouses = new HashSet<Warehouses>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddressStore() {
        return addressStore;
    }

    public void setAddressStore(String addressStore) {
        this.addressStore = addressStore;
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

    public Set<Warehouses> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouses> warehouses) {
        this.warehouses = warehouses;
    }
}