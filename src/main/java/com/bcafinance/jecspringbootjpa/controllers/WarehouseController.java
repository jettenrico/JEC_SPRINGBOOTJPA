package com.bcafinance.jecspringbootjpa.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 1:57 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.dto.SuppliersDTO;
import com.bcafinance.jecspringbootjpa.dto.WarehousesDTO;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import com.bcafinance.jecspringbootjpa.models.Stores;
import com.bcafinance.jecspringbootjpa.models.Suppliers;
import com.bcafinance.jecspringbootjpa.models.Warehouses;
import com.bcafinance.jecspringbootjpa.services.WarehouseService;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class WarehouseController {

    @Getter
    private WarehouseService warehouseService;

    @Autowired
    private ModelMapper modelMapper;

    public WarehouseController() {
    }

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/v1/warehouses")
    public ResponseEntity<Object>
    saveWarehouse(@Valid @RequestBody Warehouses warehouses) throws Exception {
        if(warehouses==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        warehouseService.saveWarehouses(warehouses);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @PostMapping("/v1/warehouses/bulk")
    public  ResponseEntity<Object>
    saveAllWarehouse(@RequestBody List <Warehouses> warehouses) throws Exception{
        if (warehouses == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        warehouseService.saveAllWarehouses(warehouses);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE_BULK, HttpStatus.CREATED,null,null,null);
    }
    @GetMapping("/v1/warehouses/{id}")
    public ResponseEntity<Object> getWarehousesById(@PathVariable("id") long id) throws Exception {
        Warehouses warehouses = warehouseService.findByIdWarehouses(id);

        if(warehouses != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,warehouses,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/warehouses/dto/{id}")
    public ResponseEntity<Object> findWarehousesByIdDTO(@PathVariable("id") long id)throws Exception{
        Warehouses warehouses = warehouseService.findByIdWarehouses(id);

        if(warehouses != null){
            WarehousesDTO warehousesDTO = modelMapper.map(warehouses, WarehousesDTO.class);
            return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, warehousesDTO, null, null);
        }else{
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/warehouses/datas/all/0")
    public ResponseEntity<Object> findAllWarehouses()throws Exception{

        List<Warehouses> lsWarehouses = warehouseService.findAllWarehouses();

        if(lsWarehouses.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsWarehouses,null,null);
    }

    @GetMapping("/v1/warehouses/datas/dto/all")
    public ResponseEntity<Object> findAllWarehousesDTO()throws Exception{

        List<Warehouses> lsWarehouses = warehouseService.findAllWarehouses();

        if(lsWarehouses.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<WarehousesDTO> lsWarehousesDTO = modelMapper.map(lsWarehouses, new TypeToken<List<WarehousesDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsWarehousesDTO,null,null);
    }

    @GetMapping("/v1/warehouses/address/search/{address}")
    public ResponseEntity<Object> getWarehouseByAddress(@PathVariable("address") String address)throws Exception{
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,warehouseService.findWarehouseByAddress(address),null,null);
    }

    @GetMapping("/v1/warehouses/address/search/dto/{address}")
    public ResponseEntity<Object> getWarehousesByAddressDTO(@PathVariable("address") String address)throws Exception{
//        Warehouses warehouses = warehouseService.findWarehouseByAddress(address);
        List<Warehouses> lsWarehouses = warehouseService.findWarehouseByAddress(address);

        if(lsWarehouses.size()==0)
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
            }
            List<WarehousesDTO> lsWarehousesDTO = modelMapper.map(lsWarehouses, new TypeToken<List<WarehousesDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsWarehousesDTO,null,null);

    }

    @PutMapping("/v1/warehouses/update")
    public ResponseEntity<Object> updateWarehouseByID(@RequestBody Warehouses warehouses)throws Exception{
        warehouseService.updateWarehouseById(warehouses);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

//    @PostMapping("/v1/warehouses/store/{id}")
//    public void addStore(@RequestBody Stores stores, @PathVariable("id") Long warehouseId) throws Exception {
//        warehouseService.addStore(stores,warehouseId);
//    }
}