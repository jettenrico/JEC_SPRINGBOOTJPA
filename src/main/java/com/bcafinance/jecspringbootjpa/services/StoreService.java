package com.bcafinance.jecspringbootjpa.services;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 2:09 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.models.Stores;
import com.bcafinance.jecspringbootjpa.models.Warehouses;
import com.bcafinance.jecspringbootjpa.repos.StoreRepo;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Store;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class StoreService {

    private StoreRepo storeRepo;

    @Autowired
    public StoreService(StoreRepo storeRepo) {
        this.storeRepo = storeRepo;
    }

    public List<Stores> findAllStores()
    {
        return storeRepo.findAll();
    }

    public List<Stores> findStoreByName(String name)
    {
        return storeRepo.findByStoreNameContaining(name);
    }

    public Stores findByIdStores(Long id) throws Exception
    {
        return storeRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public void saveStores(Stores stores) throws Exception
    {
        if(stores.getStoreName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(stores.getAddressStore()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        storeRepo.save(stores);
    }

    public void addWarehouse(Warehouses warehouses, Long storeId) throws Exception {
        Stores stores = storeRepo.findById(storeId).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_NOT_FOUND));
        stores.getWarehouses().add(warehouses);
        saveStores(stores);
    }


    @Transactional(rollbackFor = {Exception.class})
    public void saveAllStores(List<Stores> lsstr){
        storeRepo.saveAll(lsstr);
    }

    @Transactional
    public void updateStoreById(Stores s) throws Exception{
        Stores stores = storeRepo.findById(s.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_STORE_NOT_FOUND));

        stores.setModifiedBy("1");
        stores.setModifiedDate(new Date());
        if(s.getStoreName() != null
                && !Objects.equals(stores.getStoreName(),s.getStoreName())
                && !s.getStoreName().equals(""))
        {
            stores.setStoreName(s.getStoreName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(s.getAddressStore() != null
                && !Objects.equals(stores.getAddressStore(),s.getAddressStore())
                && !s.getAddressStore().equals(""))
        {
            stores.setAddressStore(s.getAddressStore());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(s.getWarehouses() != null
                && !Objects.equals(stores.getWarehouses(),s.getWarehouses())
                && !s.getWarehouses().equals(""))
        {
            stores.setWarehouses(s.getWarehouses());//BERARTI ADA PERUBAHAN DI SINI
        }
    }
}