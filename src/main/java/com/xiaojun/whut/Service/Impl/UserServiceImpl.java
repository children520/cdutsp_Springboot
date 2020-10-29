package com.xiaojun.whut.Service.Impl;

import com.xiaojun.whut.Entity.Role;
import com.xiaojun.whut.Entity.User;
import com.xiaojun.whut.Repository.RoleRepository;
import com.xiaojun.whut.Service.UserService;
import com.xiaojun.whut.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    public static final String USER_NAME_ALRREAY_EXIST="用户名已存在";
    public static final String USER_EMAIL_ALRREAY_EXIST="邮件账号已存在";
    public static final String USER_PHONE_NUMBER_ALRREAY_EXIST="用户手机号已存在";
    public static final String NOT_KNOW_EXCEPTION="未知错误";
    public static final String SUCCESS="注册成功";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String register(User newUser) {
        User user=userRepository.findByPhoneNumber(newUser.getPhoneNumber());
        Role role=roleRepository.findByName("user");
        if(user==null){
            try {
                newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                newUser.getRoles().add(role);
                userRepository.save(newUser);
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,NOT_KNOW_EXCEPTION);
            }
        }else {
            if(user.getUserName().equals(newUser.getUserName())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,USER_NAME_ALRREAY_EXIST);
            }
            if(user.getEmail().equals(newUser.getEmail())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,USER_EMAIL_ALRREAY_EXIST);
            }
            if(user.getPhoneNumber().equals(newUser.getPhoneNumber())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,USER_PHONE_NUMBER_ALRREAY_EXIST);
            }
        }
        return SUCCESS;

    }

    @Override
    public String login(String userName, String password) {
        System.out.println(userName);
        User user=userRepository.findByUserName(userName);
        Role role=roleRepository.findByName("user");
        if(user==null){
            return "该用户不存在";
        }
        if(!user.getRoles().contains(role)){
            return "没有权限进行访问！";
        }
        if(passwordEncoder.matches(password,user.getPassword())){
            return "登录成功";
        }
        return null;
    }
}
