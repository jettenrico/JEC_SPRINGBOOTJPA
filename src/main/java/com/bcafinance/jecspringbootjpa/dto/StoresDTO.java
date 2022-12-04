package com.bcafinance.jecspringbootjpa.dto;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 1:50 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.models.Stores;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class StoresDTO {

    private Long id;

    private Stores stores;
    @NotEmpty(message = ConstantMessage.WARNING_BLANK_STORES)
    private String storeName;

    @NotEmpty(message = ConstantMessage.WARNING_BLANK_ADDRESS_STORE)
    private String storeAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}