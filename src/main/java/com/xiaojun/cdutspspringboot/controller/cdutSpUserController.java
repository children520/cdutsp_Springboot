package com.xiaojun.cdutspspringboot.controller;

import com.xiaojun.cdutspspringboot.enity.User;

import com.xiaojun.cdutspspringboot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/user")
public class cdutSpUserController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/registration",produces ="application/json;charset=UTF-8")
    public @ResponseBody User addNewUser(@RequestBody User user){
        System.out.println(user.getUserName()+user.getPhoneNumber());
        String message="";
        String password=passwordEncoder.encode(user.getPassword());
        try {
            if(userRepository.existsByPhoneNumber(user.getPhoneNumber())){
                message="手机号码已注册";
                throw new DataIntegrityViolationException(message);
            }
            if(userRepository.existsByUserName(user.getUserName())){
                message="用户名已注册";
                throw new DataIntegrityViolationException(message);
            }
            user.setPassword(password);
            userRepository.save(user);
            return user;
        }catch (Exception e){
            System.out.println(e);
            if(e instanceof DataIntegrityViolationException){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,message,e);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"注册失败",e);
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

