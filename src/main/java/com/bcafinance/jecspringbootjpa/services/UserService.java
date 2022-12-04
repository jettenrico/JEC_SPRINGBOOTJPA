package com.bcafinance.jecspringbootjpa.services;


import com.bcafinance.jecspringbootjpa.configuration.ConfigProperties;
import com.bcafinance.jecspringbootjpa.core.Crypto;
import com.bcafinance.jecspringbootjpa.core.SMTPCore;
import com.bcafinance.jecspringbootjpa.handler.FormatValidation;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.models.Users;
import com.bcafinance.jecspringbootjpa.repos.UserRepo;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<Users> findAllUsers()
    {
        return userRepo.findAll();
    }

    public Users findByIdUsers(Long id) throws Exception
    {
        return userRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public List<Users> findFullNameContaining(String fullname){
        return userRepo.findByFullNameContaining(fullname);
    }
    public List<Users> findTokenLike(String token){return userRepo.findUsersByTokenLike(token);}
    public void saveUsers(Users users) throws Exception
    {
        if(users.getFullName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(users.getBirthDate()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(users.getEmail()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(users.getPassword()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        FormatValidation.emailFormatValidation(users.getEmail());
        FormatValidation.dateFormatYYYYMMDDValidation(users.getBirthDate().toString());

        Optional<Users> userByEmail = userRepo.findByEmail(users.getEmail());
        if(userByEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        String eml = users.getEmail();
        List<String> emly = new ArrayList<String>(Arrays.asList(eml.split(",")));
        String[] strArr = new String[emly.size()];

        String verifCode = new Crypto().performEncrypt(users.getCreatedDate().toString()+users.getPassword()).substring(5,11).toUpperCase();
        users.setToken(verifCode);

        userRepo.save(users);

//        System.out.println(verifCode);

        for(int i=0;i<strArr.length;i++)
        {
            strArr[i] = emly.get(i);
//            System.out.println("EMAIL KE - "+i+" : "+emly.get(i));
        }
//        System.out.println(System.getProperty("user.dir"));
        SMTPCore sc = new SMTPCore();
        ConfigProperties.getEmailPassword();
        System.out.println(sc.sendMailWithAttachment(strArr,
                "Email Verification","Please Verify Your Account Using this Link : http://localhost:8080/api/v1/a/"+verifCode,
                "SSL"
//                new String[] {ResourceUtils.getFile("classpath:\\data\\sample.docx").getAbsolutePath()}
        ));
    }

//    @Transactional
//    public void updateCustomerById(Customers c) throws Exception{
//
//        Customers customers = customerRepo.findById(c.getId()).orElseThrow(()->
//                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));
//
//        customers.setModifiedBy("1");
//        customers.setModifiedDate(new Date());
//        if(c.getFirstName() != null
//                && !Objects.equals(customers.getFirstName(),c.getFirstName())
//                && !c.getFirstName().equals(""))
//        {
//            customers.setFirstName(c.getFirstName());//BERARTI ADA PERUBAHAN DI SINI
//        }
//
//        if(c.getMiddleName() != null
//                && !Objects.equals(customers.getMiddleName(),c.getMiddleName())
//                && !c.getMiddleName().equals(""))
//        {
//            customers.setMiddleName(c.getMiddleName());//BERARTI ADA PERUBAHAN DI SINI
//        }
//
//        if(c.getEmail() != null &&
//                c.getEmail().length()>0 &&
//                !Objects.equals(customers.getEmail(),c.getEmail()))
//
//        {
//            FormatValidation.emailFormatValidation(c.getEmail());
//
//            Optional<Customers> cBeanOptional = customerRepo.findByEmail(c.getEmail());
//            if(cBeanOptional.isPresent())//it means if exists
//            {
//                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
//            }
//            customers.setEmail(c.getEmail());
//        }
//
//        if(c.getAddress() != null
//                && !Objects.equals(customers.getAddress(),c.getAddress())
//                && !c.getAddress().equals(""))
//        {
//            customers.setAddress(c.getAddress());//BERARTI ADA PERUBAHAN DI SINI
//        }
//
//        if(c.getPhoneNumber() != null &&
//                c.getPhoneNumber().length()>0 &&
//                !Objects.equals(customers.getPhoneNumber(),c.getPhoneNumber())){
//
//            FormatValidation.phoneNumberFormatValidation(c.getPhoneNumber());
//            customers.setPhoneNumber(c.getPhoneNumber());
//        }
//        if(c.getBirthDate() != null &&
//                !c.getBirthDate().toString().equals("") &&
//                !Objects.equals(customers.getBirthDate().toString(),c.getBirthDate().toString()))
//        {
//            FormatValidation.dateFormatYYYYMMDDValidation(customers.getBirthDate().toString());
//            customers.setBirthDate(c.getBirthDate());
//        }
//    }
}