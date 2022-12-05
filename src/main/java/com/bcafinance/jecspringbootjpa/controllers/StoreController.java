package com.bcafinance.jecspringbootjpa.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/30/2022
@Last Modified 11/30/2022 1:57 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.dto.StoresDTO;
import com.bcafinance.jecspringbootjpa.dto.WarehousesDTO;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import com.bcafinance.jecspringbootjpa.models.Stores;
import com.bcafinance.jecspringbootjpa.models.Warehouses;
import com.bcafinance.jecspringbootjpa.services.StoreService;
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
public class StoreController {

    @Getter
    private StoreService storeService;

    @Autowired
    private ModelMapper modelMapper;

    public StoreController() {
    }

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/v1/stores")
    public ResponseEntity<Object>
    saveStore(@Valid @RequestBody Stores stores) throws Exception {
        if(stores==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        storeService.saveStores(stores);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/stores/datas/all")
    public ResponseEntity<Object> findAllStores()throws Exception{

        List<Stores> lsStores = storeService.findAllStores();

        if(lsStores.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsStores,null,null);
    }

    @PostMapping("/v1/stores/bulk")
    public  ResponseEntity<Object>
    saveAllStore(@RequestBody List <Stores> stores) throws Exception{
        if (stores == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        storeService.saveAllStores(stores);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE_BULK, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/stores/{id}")
    public ResponseEntity<Object> getStoresById(@PathVariable("id") long id) throws Exception {
        Stores stores = storeService.findByIdStores(id);

        if(stores != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,stores,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/stores/dto/{id}")
    public ResponseEntity<Object> findStoresByIdDTO(@PathVariable("id") long id)throws Exception{
        Stores stores = storeService.findByIdStores(id);

        if(stores != null){
            StoresDTO storesDTO = modelMapper.map(stores, StoresDTO.class);
            return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, storesDTO, null, null);
        }else{
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }


    @GetMapping("/v1/stores/datas/dto/all")
    public ResponseEntity<Object> findAllStoresDTO()throws Exception{

        List<Stores> lsStores = storeService.findAllStores();

        if(lsStores.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<StoresDTO> lsStoresDTO = modelMapper.map(lsStores, new TypeToken<List<StoresDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsStoresDTO,null,null);
    }

    @GetMapping("/v1/stores/storename/search/{name}")
    public ResponseEntity<Object> getStoreByName(@PathVariable("name") String name)throws Exception{
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,storeService.findStoreByName(name),null,null);
    }

    @GetMapping("/v1/stores/storename/search/dto/{name}")
    public ResponseEntity<Object> getStoresByNameDTO(@PathVariable("name") String name)throws Exception{
        List<Stores> lsStores = storeService.findStoreByName(name);

        if(lsStores.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<StoresDTO> lsStoresDTO = modelMapper.map(lsStores, new TypeToken<List<StoresDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsStoresDTO,null,null);

    }
//
    @PutMapping("/v1/stores/update")
    public ResponseEntity<Object> updateStoreByID(@RequestBody Stores stores)throws Exception{
        storeService.updateStoreById(stores);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/v1/stores/whouse/{id}")
    public ResponseEntity<Object> addWarehouse(@RequestBody Warehouses warehouses, @PathVariable("id") Long storeId) throws Exception {
        if (warehouses == null && storeId == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        storeService.addWarehouse(warehouses, storeId);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.OK,"",null,null);
    }
}