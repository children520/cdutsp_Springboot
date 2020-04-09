package com.example.cdutspspringboot;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = test.class)
@EnableEncryptableProperties
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    StringEncryptor encryptor;

    @Test
    public void  test(){
        System.out.println(encryptor.encrypt("520920"));
    }
}
