package com.bcafinance.jecspringbootjpa.controllers;


import com.bcafinance.jecspringbootjpa.configuration.ConfigProperties;
import com.bcafinance.jecspringbootjpa.core.SMTPCore;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.models.Users;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/taketour")
public class TakeTourController {

    @GetMapping("/welcome")
    public String getTakeTour(){
        return ConstantMessage.WELCOME_MESSAGE;
    }

    @PostMapping("/readytostart")
    public String postTakeTour(){
        return ConstantMessage.TAKE_TOUR;
    }

//    @GetMapping("/runnerz")
//    public ResponseEntity<Object> executionClass(@RequestBody List<String> lsEmail, Users users) throws Exception {
//        if(users==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
//
//        String[] strArr = new String[lsEmail.size()];
//
//        for(int i=0;i<strArr.length;i++)
//        {
//            strArr[i] = lsEmail.get(i);
////            System.out.println("EMAIL KE - "+i+" : "+lsEmail.get(i));
//        }
//        System.out.println(System.getProperty("user.dir"));
//        SMTPCore sc = new SMTPCore();
//        ConfigProperties.getEmailPassword();
//        System.out.println(sc.sendMailWithAttachment(strArr,
//                "INI HANYA TEST",new ReadTextFileSB("\\data\\template-BCAF.html").getContentFile(),
//                "SSL"
////                new String[] {ResourceUtils.getFile("classpath:\\data\\sample.docx").getAbsolutePath()}
//        ));
//        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SEND_EMAIL, HttpStatus.CREATED,null,null,null);
//    }
}
