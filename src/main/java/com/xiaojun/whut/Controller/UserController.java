package com.xiaojun.whut.Controller;

import com.xiaojun.whut.Entity.User;
import com.xiaojun.whut.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @PostMapping(path = "/register")
    public @ResponseBody User addNewUser(@RequestBody User user){
        String message="";
        String encodePassword=passwordEncoder.encode(user.getPassword());
        System.out.println(user.getUserName());
        try {
            if(userRepository.existsByEmail(user.getEmail())){
                message="邮箱已被注册";
                throw new DataIntegrityViolationException(message);
            }
            if(userRepository.existsByUserName(user.getUserName())){
                message="用户名已被注册";
                throw new DataIntegrityViolationException(message);
            }
            if (userRepository.existsByPhoneNumber(user.getPhoneNumber())){
                message="号码已被注册";
                throw new DataIntegrityViolationException(message);
            }

            user.setPassword(encodePassword);
            userRepository.save(user);
            return user;
        }catch (Exception e){
            System.out.println(message);
            if(e instanceof DataIntegrityViolationException){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,message,e);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"注册失败",e);
        }
    }
}
