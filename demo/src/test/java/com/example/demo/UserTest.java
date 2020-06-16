package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    void insert(){
        User user = new User();
        user.setNickname("ojh031");
        user.setName("오주훈");
        user.setPassword("1q2w3e4r");
        user.setEmail("ojh031@icloud.com");

        userService.insertUser(user);
    }
}
