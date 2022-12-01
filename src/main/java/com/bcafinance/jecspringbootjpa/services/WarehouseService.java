package com.bcafinance.jecspringbootjpa.services;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 2:09 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.handler.FormatValidation;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.models.Warehouses;
import com.bcafinance.jecspringbootjpa.repos.WarehouseRepo;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class WarehouseService {

    private WarehouseRepo warehouseRepo;

    @Autowired
    public WarehouseService(WarehouseRepo supplierRepo) {
        this.warehouseRepo = supplierRepo;
    }

    public List<Warehouses> findAllWarehouses()
    {
        return warehouseRepo.findAll();
    }

    public Warehouses findByIdWarehouses(Long id) throws Exception
    {
        return warehouseRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public List<Warehouses> findWarehouseByAddress(String address)
    {
        return warehouseRepo.findByAddressContaining(address);
    }

    public void saveWarehouses(Warehouses warehouses) throws Exception
    {
        if(warehouses.getAddress()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(warehouses.getWarehouseSpv()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(warehouses.getWarehouseDesc()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        warehouseRepo.save(warehouses);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllWarehouses(List<Warehouses> lswar){
        warehouseRepo.saveAll(lswar);
    }

    @Transactional
    public void updateWarehouseById(Warehouses w) throws Exception{

        Warehouses warehouses = warehouseRepo.findById(w.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_WAREHOUSE_NOT_FOUND));

        warehouses.setModifiedBy("1");
        warehouses.setModifiedDate(new Date());
        if(w.getAddress() != null
                && !Objects.equals(warehouses.getAddress(),w.getAddress())
                && !w.getAddress().equals(""))
        {
            warehouses.setAddress(w.getAddress());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(w.getWarehouseSpv() != null
                && !Objects.equals(warehouses.getWarehouseSpv(),w.getWarehouseSpv())
                && !w.getWarehouseSpv().equals(""))
        {
            warehouses.setWarehouseSpv(w.getWarehouseSpv());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(w.getWarehouseDesc() != null
                && !Objects.equals(warehouses.getWarehouseDesc(),w.getWarehouseDesc())
                && !w.getWarehouseDesc().equals(""))
        {
            warehouses.setWarehouseDesc(w.getWarehouseDesc());//BERARTI ADA PERUBAHAN DI SINI
        }
    }
}