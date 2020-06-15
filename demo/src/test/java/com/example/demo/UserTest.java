package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    void insert(){
        User user= new User();
        user.setCurrent(0L);
        user.setUid("ohjuhun");
        user.setName("주훈");
        user.setPassword("12345");


        userService.insertUser(user);
    }
}
