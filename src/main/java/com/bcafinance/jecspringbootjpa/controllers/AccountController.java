package com.bcafinance.jecspringbootjpa.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 12/5/2022
@Last Modified 12/5/2022 1:21 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.dto.AccountsDTO;
import com.bcafinance.jecspringbootjpa.models.Accounts;
import com.bcafinance.jecspringbootjpa.services.AccountService;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class AccountController {

    @Getter
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/v1/transfer")
    public ResponseEntity<Object>
    transferRekening(@Valid @RequestBody AccountsDTO accountsDTO) throws Exception {
        if(accountsDTO == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        accountService.transferMoney(accountsDTO);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_TRANSFER,
                    HttpStatus.OK,null,null,null);
//        try{
//            if(RekAnda == null || RekAnda == "") throw new ResourceNotFoundException(ConstantMessage.WARNING_REK_SUMBER_NULL);
//            else if (RekTujuan == null || RekTujuan == "") {
//                throw new ResourceNotFoundException(ConstantMessage.WARNING_REK_TUJUAN_NULL);
//            } else if ( Jumlah == 0) {
//                throw new ResourceNotFoundException(ConstantMessage.WARNING_REK_JUMLAH_EMPTY);
//            }
//            accountService.transferMoney(RekAnda, RekTujuan, Jumlah);
//            return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_TRANSFER,
//                    HttpStatus.OK,null,null,null);
//        }catch (Exception e)
//        {
//            throw new Exception(ConstantMessage.ERROR_NO_CONTENT);
//        }
    }

    @PostMapping("/v1/accounts")
    public ResponseEntity<Object>
    saveAccount(@RequestBody Accounts accounts) throws Exception {
        if(accounts==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        accountService.saveAccounts(accounts);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_ADD_REKENING, HttpStatus.CREATED,null,null,null);
    }
}