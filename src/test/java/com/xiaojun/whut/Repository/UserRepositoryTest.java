package com.xiaojun.whut.Repository;

import com.xiaojun.whut.Entity.Role;
import com.xiaojun.whut.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void save(){
        User user=new User();
        user.setPhoneNumber("13487073806");
        user.setEmail("1306410285@qq.com");
        user.setUserName("ffaq");
        Set<Role> list=new HashSet<>();

        Role role1=new Role();
        role1.setNameZH("系统管理员");
        role1.setName("role_admin");
        list.add(role1);

        Role role2=new Role();
        role2.setNameZH("数据库管理员");
        role2.setName("dba_admin");
        list.add(role2);

        Role role3=new Role();
        role3.setNameZH("用户");
        role3.setName("user");
        list.add(role3);

        //Role role=roleRepository.findByName("user");
        user.getRoles().addAll(list);


        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        user.setSex(User.Sex.Boy);
        user.setEnable(true);
        user.setCollage("计科院");
        user.setLocked(false);

        userRepository.save(user);
    }
    @Test
    public void login(){
        User user=userRepository.findByUserName("ffaq");
        System.out.println(bCryptPasswordEncoder.matches("1234", user.getPassword()));
    }
}