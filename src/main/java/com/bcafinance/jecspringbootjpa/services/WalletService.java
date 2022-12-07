package com.bcafinance.jecspringbootjpa.services;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 12/5/2022
@Last Modified 12/5/2022 1:21 PM
Version 1.0
*/
import com.bcafinance.jecspringbootjpa.dto.WalletsDTO;
import com.bcafinance.jecspringbootjpa.models.Wallets;
import com.bcafinance.jecspringbootjpa.repos.WalletRepo;
import com.bcafinance.jecspringbootjpa.utils.CsvReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WalletService {

    @Getter
    private WalletRepo walletRepo;

    @Autowired
    public WalletService(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Wallets> saveBulkWallet(MultipartFile multipartFile) throws Exception
    {
        try{
            List<Wallets> lsWallet = CsvReader.csvToWalletData(multipartFile.getInputStream());
            return walletRepo.saveAll(lsWallet);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Wallets> findAllWallet()
    {
        return (List<Wallets>)walletRepo.findAll();
    }

    public Page<Wallets> pagingFindWalletByName(String name, Pageable pageable)
    {
        return walletRepo.findByFirstNameContaining(name,pageable);
    }

    public Map<String, Object> mappingDto(Page<Wallets> m, List<WalletsDTO> lsDto){
        Map<String,Object> mapz = new HashMap<>();
        mapz.put("content", lsDto);
        mapz.put("currentPage", m.getNumber());
        mapz.put("totalItems", m.getTotalElements());
        mapz.put("totalPages", m.getTotalPages());
        mapz.put("numberOfElements", m.getNumberOfElements());
        mapz.put("pageable", m.getPageable());
        return mapz;
    }
}
