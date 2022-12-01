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
import com.bcafinance.jecspringbootjpa.models.Suppliers;
import com.bcafinance.jecspringbootjpa.repos.SupplierRepo;
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
public class SupplierService {

    private SupplierRepo supplierRepo;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public List<Suppliers> findAllSuppliers()
    {
        return supplierRepo.findAll();
    }

    public Suppliers findByIdSuplliers(Long id) throws Exception
    {
        return supplierRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public Suppliers findByEmailSuppliers(String email) throws Exception
    {

        return supplierRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_SUPPLIER_EMAIL_NOT_FOUND));
    }

    public Suppliers findByCompanyName(String name) throws Exception
    {
        return supplierRepo.findSuppliersByCompanyName(name).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND_SUPPLIER));
    }

    public List<Suppliers> findCompanyNameContaining(String name)
    {
        return supplierRepo.findByCompanyNameContaining(name);
    }

    public List<Suppliers> findCompanyNameStartWith(String name){
        return supplierRepo.findByCompanyNameStartsWith(name);
    }

    public List<Suppliers> findSupervisorNotContaining(String name)
    {
        return supplierRepo.findBySupervisorNameNotContaining(name);
    }

    public List<Suppliers> findSupervisorEndWith(String name)
    {
        return supplierRepo.findBySupervisorNameEndsWith(name);
    }

    public void saveSuppliers(Suppliers suppliers) throws Exception
    {
        if(suppliers.getEmail()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(suppliers.getCompanyName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(suppliers.getAddress()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(suppliers.getSupervisorName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(suppliers.getPhoneNumber()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(suppliers.getWarehouses()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        FormatValidation.phoneNumberFormatValidation(suppliers.getPhoneNumber());
        FormatValidation.emailFormatValidation(suppliers.getEmail());

        Optional<Suppliers> suppByEmail = supplierRepo.findByEmail(suppliers.getEmail());
        if(suppByEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        supplierRepo.save(suppliers);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllSuppliers(List<Suppliers> lssup){
        supplierRepo.saveAll(lssup);
    }

    @Transactional
    public void updateSupplierById(Suppliers s) throws Exception{

        Suppliers suppliers = supplierRepo.findById(s.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));

        suppliers.setModifiedBy("1");
        suppliers.setModifiedDate(new Date());
        if(s.getCompanyName() != null
                && !Objects.equals(suppliers.getCompanyName(),s.getCompanyName())
                && !s.getCompanyName().equals(""))
        {
            suppliers.setCompanyName(s.getCompanyName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(s.getSupervisorName() != null
                && !Objects.equals(suppliers.getSupervisorName(),s.getSupervisorName())
                && !s.getSupervisorName().equals(""))
        {
            suppliers.setCompanyName(s.getCompanyName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(s.getEmail() != null &&
                s.getEmail().length()>0 &&
                !Objects.equals(suppliers.getEmail(),s.getEmail()))

        {
            FormatValidation.emailFormatValidation(s.getEmail());

            Optional<Suppliers> cBeanOptional = supplierRepo.findByEmail(s.getEmail());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
            }
            suppliers.setEmail(s.getEmail());
        }

        if(s.getAddress() != null
                && !Objects.equals(suppliers.getAddress(),s.getAddress())
                && !s.getAddress().equals(""))
        {
            suppliers.setAddress(s.getAddress());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(s.getPhoneNumber() != null &&
                s.getPhoneNumber().length()>0 &&
                !Objects.equals(suppliers.getPhoneNumber(),s.getPhoneNumber())){

            FormatValidation.phoneNumberFormatValidation(s.getPhoneNumber());
            suppliers.setPhoneNumber(s.getPhoneNumber());
        }
    }
}