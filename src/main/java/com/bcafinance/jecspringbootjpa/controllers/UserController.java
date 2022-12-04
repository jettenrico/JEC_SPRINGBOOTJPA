package com.bcafinance.jecspringbootjpa.controllers;

import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import com.bcafinance.jecspringbootjpa.models.Users;
import com.bcafinance.jecspringbootjpa.services.UserService;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class UserController {
    @Getter
    private UserService userService;


    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/users")
    public ResponseEntity<Object>
    saveUser(@RequestBody Users users) throws Exception {
        if(users==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);

        userService.saveUsers(users);

        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SEND_EMAIL, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<Object> getUsersById(@PathVariable("id") long id) throws Exception {
        Users users = userService.findByIdUsers(id);

        if(users != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,users,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/users/datas/all")
    public ResponseEntity<Object> findAllUsers()throws Exception{
        List<Users> lsUsers = userService.findAllUsers();

        if(lsUsers.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsUsers,null,null);
    }

    @GetMapping("/v1/users/fullname/{name}")
    public ResponseEntity<Object> getUserContaining(@PathVariable("name") String name)throws Exception{
        List<Users> lsUsers = userService.findFullNameContaining(name);

        if(lsUsers.size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_FAILED_AUTHENTICATION);
        }else {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,userService.findFullNameContaining(name),null,null);
        }
    }

    @GetMapping("/v1/a/{token}")
    public ResponseEntity<Object> getUserTokenContaining(@PathVariable("token") String token)throws Exception{
        List<Users> lsUsers = userService.findTokenLike(token);

        if(lsUsers.size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_FAILED_AUTHENTICATION);
        }else {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_AUTHENTICATION,HttpStatus.CREATED,null,null,null);
        }
    }
}
