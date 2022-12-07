package com.bcafinance.jecspringbootjpa.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 12/5/2022
@Last Modified 12/5/2022 1:21 PM
Version 1.0
*/
import com.bcafinance.jecspringbootjpa.dto.WalletsDTO;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.models.Wallets;
import com.bcafinance.jecspringbootjpa.services.WalletService;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import com.bcafinance.jecspringbootjpa.utils.CsvReader;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class WalletController {

    @Getter
    private WalletService walletService;
    @Autowired
    private ModelMapper modelMapper;

    private List<Wallets> lsWallet = new ArrayList<Wallets>();

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/v1/wallet/upload/csv")
    public ResponseEntity<Object>
    uploadWallet(@Valid @RequestParam("demoFile") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReader.isCsv(multipartFile))
            {
                walletService.saveBulkWallet(multipartFile);
                return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,
                        HttpStatus.CREATED,null,null,null);
            }
            else
            {
                throw new ResourceNotFoundException(ConstantMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename());
            }
        }catch (Exception e)
        {
            throw new Exception(ConstantMessage.ERROR_UPLOAD_CSV+multipartFile.getOriginalFilename());
        }
    }

    @GetMapping("/v1/wallet/datas/all/dto")
    public ResponseEntity<Object> findAllWalletDTO()throws Exception {

        List<Wallets> lsWallet = walletService.findAllWallet();

        if(lsWallet.size()!=0)
        {
            List<WalletsDTO> lsWalletDTO = modelMapper.map(lsWallet, new TypeToken<List<WalletsDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsWalletDTO,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }

    @GetMapping("/v1/wallet/search/dto/{size}/{page}")
    public ResponseEntity<Object> pageFindWalletByNameDTO(@RequestParam String name,
                                                           @PathVariable("size") int size,
                                                           @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,walletService.pagingFindWalletByName(name,pageable),null,null);
    }

    @GetMapping("/v1/wallet/search/dto/{size}/{page}/{sort}")
    public ResponseEntity<Object> pageSortByNameDTO(@RequestParam String name,
                                                    @PathVariable("size") int size,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("sort") String sortz)throws Exception {
        Pageable pageable;
        if(sortz.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }
        else
        {
            pageable = PageRequest.of(page,size, Sort.by("id"));//default asc
        }
        Page<Wallets> m = walletService.pagingFindWalletByName(name,pageable);
        List<Wallets> ls = m.getContent();
        List<WalletsDTO> lsDto = modelMapper.map(ls, new TypeToken<List<WalletsDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK, walletService.mappingDto(m, lsDto)
                        ,null,null);

//        YANG BAWAH INI BERANTAKAN
//        Pageable pageable;
//        if(sortz.equalsIgnoreCase("desc"))
//        {
//            pageable = PageRequest.of(page,size, Sort.by("id").descending());
//        }
//        else
//        {
//            pageable = PageRequest.of(page,size, Sort.by("id"));//default asc
//        }
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,walletService.pagingFindWalletByName(name,pageable),null,null);
    }
}