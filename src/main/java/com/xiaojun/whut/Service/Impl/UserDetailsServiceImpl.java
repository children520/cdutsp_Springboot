package com.xiaojun.whut.Service.Impl;

import com.xiaojun.whut.Entity.Role;
import com.xiaojun.whut.Entity.User;
import com.xiaojun.whut.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(userName);
        if(user==null){
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthoritySet=new HashSet<>();
        for (Role role:user.getRoles()){
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                grantedAuthoritySet);
    }
}
