package com.xiaojun.whut.Service;


import com.xiaojun.whut.Entity.User;

public interface UserService{
    public String register(User user);

    public String login(String userName,String password);
}
