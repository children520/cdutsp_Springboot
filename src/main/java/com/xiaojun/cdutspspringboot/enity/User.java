package com.xiaojun.cdutspspringboot.enity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userName","phoneNumber"}))
public class User {
    public static enum Sex{
        Boy,Girl
    }

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;



    @Column(nullable = false,length = 10)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,length = 5)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false,length = 20)
    private String collage;

    @Id
    @Column(nullable = false,length = 20)
    private String phoneNumber;



    public User() {
    }

    public User(String userName, String password,
                Sex sex, String collage, String phoneNumber){
        this.userName=userName;
        this.password=password;
        this.collage=collage;
        this.sex=sex;
        this.phoneNumber=phoneNumber;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }




    public String getPassword() {
        return password;
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

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }
}
