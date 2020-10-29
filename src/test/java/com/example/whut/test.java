package com.example.whut;

import com.xiaojun.whut.Entity.Role;
import com.xiaojun.whut.Repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@EntityScan(basePackages = "com.xiaojun.whut.repository")
@SpringBootTest(classes = test.class)
@RunWith(SpringRunner.class)
public class test {

    private JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
    @Autowired
    private RoleRepository roleRepository;
    @Test
    public void  test(){

        SimpleMailMessage message= new SimpleMailMessage();
        try {
            message.setFrom("1306410285@qq.com");
            message.setTo("3042616487@qq.com");
            message.setText("xiaojun");
            System.out.println(message);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void PasswordTest(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "anDi_T_X_D_Y";
        String encode = encoder.encode(password);
        System.out.println("第一次加密" + encode);
        String encode1 = encoder.encode(password);
        System.out.println("第一次加密" + encode1);
        System.out.println("第一次加密密文是否验证通过: " + encoder.matches(password, encode));
        System.out.println("第二次加密密文是否验证通过: " + encoder.matches(password, encode1));
    }
    @Test
    public void save(){
        String roleStr="ROLE_";
        Role role=new Role();
        role.setNameZH("系统管理员");
        role.setName(roleStr+"admin");
        roleRepository.save(role);
    }
}
