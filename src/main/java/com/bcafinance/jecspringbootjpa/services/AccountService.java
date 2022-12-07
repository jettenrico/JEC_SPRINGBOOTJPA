package com.bcafinance.jecspringbootjpa.services;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 12/5/2022
@Last Modified 12/5/2022 1:21 PM
Version 1.0
*/
import com.bcafinance.jecspringbootjpa.configuration.ConfigProperties;
import com.bcafinance.jecspringbootjpa.core.Crypto;
import com.bcafinance.jecspringbootjpa.core.SMTPCore;
import com.bcafinance.jecspringbootjpa.dto.AccountsDTO;
import com.bcafinance.jecspringbootjpa.handler.FormatValidation;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import com.bcafinance.jecspringbootjpa.models.Accounts;
import com.bcafinance.jecspringbootjpa.models.Users;
import com.bcafinance.jecspringbootjpa.repos.AccountRepo;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Getter
    private AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferMoney(AccountsDTO accountsDTO) throws Exception
    {
        try{
            Accounts accountSource = accountRepo.findByAccountNumber(accountsDTO.getAccountNumberSource());
            Accounts accountDestination = accountRepo.findByAccountNumber(accountsDTO.getAccountNumberDestination());

            if(accountSource==null) throw new ResourceNotFoundException(ConstantMessage.WARNING_REK_SOURCE_EMPTY);
            if(accountDestination==null) throw new ResourceNotFoundException(ConstantMessage.WARNING_REK_DESTINATION_EMPTY);

            if(accountSource.getBalance() < accountsDTO.getAmount()) {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_SALDO);
            }else {
                accountSource.setBalance(accountSource.getBalance() - accountsDTO.getAmount());
                accountDestination.setBalance(accountDestination.getBalance() + accountsDTO.getAmount());
            }

//            Accounts accountsSource;
//            Accounts accountsDestination;
//            accountsSource = accountRepo.findSourceAccountsByAccountNumberLike(source);
//            accountsDestination = accountRepo.findDestinationAccountsByAccountNumberLike(destination);
//
//            if(accountsSource.getBalance() < money){
//                throw new ResourceNotFoundException(ConstantMessage.WARNING_SALDO);
//            }else {
//                accountsSource.setBalance(accountsSource.getBalance() - money);
//                accountsDestination.setBalance(accountsDestination.getBalance() + money);
//            }
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public void saveAccounts(Accounts accounts) throws Exception
    {
        if(accounts.getAccountNumber()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(accounts.getBalance()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        accountRepo.save(accounts);
    }

//    public List<Wallets> findAllWallet()
//    {
//        return (List<Wallets>)walletRepo.findAll();
//    }
//
//    public Page<Wallets> pagingFindWalletByName(String name, Pageable pageable)
//    {
//        return walletRepo.findByFirstNameContaining(name,pageable);
//    }
}
