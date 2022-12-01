package com.bcafinance.jecspringbootjpa.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 1:57 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import com.bcafinance.jecspringbootjpa.models.Suppliers;
import com.bcafinance.jecspringbootjpa.services.SupplierService;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class SupplierController {

    @Getter
    private SupplierService supplierService;


    public SupplierController() {
    }

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/v1/suppliers")
    public ResponseEntity<Object>
    saveSupplier(@Valid @RequestBody Suppliers suppliers) throws Exception {
        if(suppliers==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        supplierService.saveSuppliers(suppliers);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @PostMapping("/v1/suppliers/bulk")
    public  ResponseEntity<Object>
    saveAllSupplier(@RequestBody List <Suppliers> suppliers) throws Exception{
        if (suppliers == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        supplierService.saveAllSuppliers(suppliers);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE_BULK, HttpStatus.CREATED,null,null,null);
    }
    @GetMapping("/v1/suppliers/{id}")
    public ResponseEntity<Object> getSuppliersById(@PathVariable("id") long id) throws Exception {
        Suppliers suppliers = supplierService.findByIdSuplliers(id);

        if(suppliers != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,suppliers,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/suppliers/datas/all/0")
    public ResponseEntity<Object> findAllSuppliers()throws Exception{

        int data = 0;
        List<Suppliers> lsSuppliers = supplierService.findAllSuppliers();

        if(lsSuppliers.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsSuppliers,null,null);
    }

    @GetMapping("/v1/suppliers/datas/search/{email}")
    public ResponseEntity<Object> getSupplierByEmail(@PathVariable("email") String email)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,supplierService.findByEmailSuppliers(email),null,null);
    }

    @GetMapping("/v1/suppliers/datas/search/t/{name}")
    public ResponseEntity<Object> getSupplierByName(@PathVariable("name") String name)throws Exception{
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,supplierService.findByCompanyName(name),null,null);
    }

    @GetMapping("/v1/suppliers/companies/{name}")
    public ResponseEntity<Object> getCompanyContaining(@PathVariable("name") String name)throws Exception{
        List<Suppliers> lsSuppliers = supplierService.findCompanyNameContaining(name);

        if(lsSuppliers.size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND_SUPPLIER);
        }else {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,supplierService.findCompanyNameContaining(name),null,null);
        }
    }

    @GetMapping("/v1/suppliers/companies/starts/{name}")
    public ResponseEntity<Object> getCompanyNameStartWih(@PathVariable("name") String name)throws Exception{
        List<Suppliers> lsSuppliers = supplierService.findCompanyNameStartWith(name);

        if(lsSuppliers.size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND_SUPPLIER);
        }else {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,supplierService.findCompanyNameStartWith(name),null,null);
        }
    }

    @GetMapping("/v1/suppliers/supervisors/{name}")
    public ResponseEntity<Object> getSupervisorNotContaining(@PathVariable("name") String name)throws Exception{
        List<Suppliers> lsSuppliers = supplierService.findSupervisorNotContaining(name);

        if(lsSuppliers.size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND_SUPERVISOR);
        }else {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,supplierService.findSupervisorNotContaining(name),null,null);
        }
    }

    @GetMapping("/v1/suppliers/supervisors/ends/{name}")
    public ResponseEntity<Object> getSupervisorEndWith(@PathVariable("name") String name)throws Exception{
        List<Suppliers> lsSuppliers = supplierService.findSupervisorEndWith(name);

        if(lsSuppliers.size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND_SUPERVISOR);
        }else {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,supplierService.findSupervisorEndWith(name),null,null);
        }
    }

    @PutMapping("/v1/suppliers/t")
    public ResponseEntity<Object> updateSupplierByID(@RequestBody Suppliers suppliers)throws Exception{
        supplierService.updateSupplierById(suppliers);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }
}