package com.xiaojun.whut.Repository;

import com.xiaojun.whut.Entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void save(){
        String roleStr="role_";
        Role role=new Role();
        role.setNameZH("系统管理员");
        role.setName(roleStr+"admin");
        roleRepository.save(role);
    }
}