package com.example.demo;

import com.example.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
public class UserTest extends AbstractControllerTest {

    @Autowired
    UserController userController;

    @Override
    protected Object controller(){
        return userController;
    }

    @Test
    void findNameById() throws Exception{
    }
    @Test
    void findAll() throws Exception {
        userController.getAll();
        mockMvc.perform(get("/user"))
                .andExpect(content().string("ojh")); //이걸 해라
    }
    @Test
    void insert(){

    }
    @Test
    void modifyPasswordTest(){

    }

    @Test
    void getByNameTest(){

    }
}
