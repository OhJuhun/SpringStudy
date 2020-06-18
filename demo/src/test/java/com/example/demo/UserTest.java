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
    static final String base = "/user";
    @Autowired
    UserController userController;

    @Override
    protected Object controller(){
        return userController;
    }

    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(get(base))
                .andExpect(status().isOk());
    }

    @Test
    void findByNameTest() throws Exception{
        final String additional = "/getByName?";
        final String key = "name";
        final String value = "Jonny";
        final String uri = base+additional+key+"="+value;
        mockMvc.perform(get(uri))
                .andExpect(status().isOk());
    }

    @Test
    void insertTest() throws Exception{
        mockMvc.perform(post(base)
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
        mockMvc.perform(patch(base+"/modifyEmail?nickname=ojh031&password=dhwngns&newEmail=newEmail2@naver.com")
                ).andExpect(status().isOk());
    }
    @Test
    void modifyPasswordTest() throws Exception {
        mockMvc.perform(patch(base+"/modifyPassword?nickname=ojh031&password=dhwngns&newPassword=dhwngns2")
                ).andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception{
        mockMvc.perform(delete(base+"?nickname=ojh03177&password=1q2w3e4r")
                ).andExpect(status().isOk());
    }
}
