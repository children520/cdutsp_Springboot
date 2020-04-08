package com.xiaojun.cdutspspringboot.controller;

import com.xiaojun.cdutspspringboot.Enity.User;

import com.xiaojun.cdutspspringboot.exception.UserAlreadyExistException;
import com.xiaojun.cdutspspringboot.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping(path = "/user")
public class cdutSpUserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/registration",produces ="application/json;charset=UTF-8")
    public @ResponseBody User addNewUser(@RequestBody User user){
        String message="";
        try {
            if(userRepository.existsByPhoneNumber(user.getPhoneNumber())){
                message="手机号码已注册";
                throw new DataIntegrityViolationException(message);
            }
            if(userRepository.existsByUserName(user.getUserName())){
                message="用户名已注册";
                throw new DataIntegrityViolationException(message);
            }
            userRepository.save(user);
            return user;
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,message,e);

        }
    }
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    /*
    @GetMapping(path = "/userName/{userName}")
    public @ResponseBody String searchUserName(@PathVariable("userName") String userName)  {
        System.out.println(userName);
        try {
            if(userRepository.existsByUserName(userName)){
                throw new SQLIntegrityConstraintViolationException();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"username already exist",e);
        }
        return "注册失败";
    }

    @GetMapping(path = "/phoneNumber/{phoneNumber}")
    public @ResponseBody String searchPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        try {
            if(userRepository.existsByPhoneNumber(phoneNumber)){
                throw new SQLIntegrityConstraintViolationException();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"phoneNumber already exist",e);
        }
        return "注册成功";
    }

     */
}

