package com.xiaojun.whut.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userName","email","phoneNumber"}))
public class User implements UserDetails {

    public enum Sex{
        Boy,Girl;

    }

    @Column(nullable = false,length = 10)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,length = 4)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false,length = 20)
    private String collage;


    @Column(nullable = false,length = 20)
    private String email;

    @Id
    @Column(nullable = false,length = 11)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "phoneNumber")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")})
    private Set<Role> roles =new HashSet<>();

    @Column(nullable = false)
    private boolean enable;
    @Column(nullable = false)
    private boolean locked;

    public User() {

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;

    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
