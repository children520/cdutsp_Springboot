package com.xiaojun.whut.Controller;

import com.xiaojun.whut.Entity.Role;
import com.xiaojun.whut.Entity.User;
import com.xiaojun.whut.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class testController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/test")
    public String test(){
        try {
            User user=new User();
            user.setPhoneNumber("13487073802");
            user.setEmail("1306410285@qq.com");
            user.setUserName("children");
            List<Role> list=new LinkedList<>();
            Role role=new Role();
            role.setNameZH("系统管理员");
            role.setName("admin");
            list.add(role);
            //user.setRoles(list);
            user.setPassword("1234");
            user.setSex(User.Sex.Boy);
            user.setEnable(true);
            user.setCollage("计科院");
            user.setLocked(false);
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }
        return "succ";
    }
}
