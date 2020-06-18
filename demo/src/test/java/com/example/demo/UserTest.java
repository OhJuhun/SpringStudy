package com.example.demo;

import com.example.demo.controller.UserController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
public class UserTest extends AbstractControllerTest {

    @Autowired
    UserController userController;

    @Override
    protected Object controller(){
        return userController;
    }

    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    void findByNameTest() throws Exception{
        mockMvc.perform(get("/user/getByName?name=Jonny"))
                .andExpect(status().isOk());
    }

    @Test
    void insertTest() throws Exception{
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nickname\":\"ojh03177\"," +
                        "\"name\":\"오훈\"," +
                        "\"password\":\"1q2w3e4r\"," +
                        "\"email\":\"ojh031@netmarble.com\"" +
                        "}")) //이게 최선은 아닐거 같은데
                .andExpect(status().isOk());
    }

    @Test
    void modifyEmailTest() throws Exception{
        mockMvc.perform(patch("/user/modifyEmail?nickname=ojh031&password=dhwngns&newEmail=newEmail2@naver.com")
                ).andExpect(status().isOk());
    }
    @Test
    void modifyPasswordTest() throws Exception {
        mockMvc.perform(patch("/user/modifyPassword?nickname=ojh031&password=dhwngns&newPassword=dhwngns2")
                ).andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception{
        mockMvc.perform(delete("/user?nickname=ojh03177&password=1q2w3e4r")
                ).andExpect(status().isOk());
    }
}
