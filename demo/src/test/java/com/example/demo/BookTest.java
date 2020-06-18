package com.example.demo;

import com.example.demo.controller.BookController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class BookTest extends AbstractControllerTest{


    static final String base = "/book";
    @Autowired
    BookController bookController;

    @Override
    protected Object controller() {return bookController;}


    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(get(base))
                .andExpect(status().isOk());
    }

    @Test
    void findByIsbntest() throws Exception{
        final String additional = "/getByIsbn";
        mockMvc.perform(get(base+additional)
                .param("isbn","100000000001"))
                .andExpect(status().isOk());
    }

    @Test
    void findByNameTest() throws Exception{
        final String additional = "/getByName";
        mockMvc.perform(get(base+additional)
                .param("name","Java 8"))
                .andExpect(status().isOk());
    }

    @Test
    void insertTest() throws Exception{
        mockMvc.perform(post(base)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        +
                        "\"name\":\"Java 9\","
                        + "\"isbn\":\"100000000005\","
                        + "\"quantity\":3,"
                        + "}")
        ).andExpect(status().isOk()); //이게 최선은 아닐거 같은데

    }

    @Test
    void modifyNameTest() throws Exception {
        final String additional = "/modifyName";
        mockMvc.perform(patch(base+additional)
                .param("isbn","100000000001")
                .param("newName","Java 8 SE"))
                .andExpect(status().isOk());
    }

    @Test
    void modifyIsbnTest() throws Exception {
        final String additional = "/modifyIsbn";
        mockMvc.perform(patch(base+additional)
                .param("isbn","100000000001")
                .param("newIsbn","100000000010"))
                .andExpect(status().isOk());
    }

    @Test
    void modifyQuantityTest() throws Exception {
        final String additional = "/modifyQuantity";
        mockMvc.perform(patch(base+additional)
                .param("isbn","100000000001")
                .requestAttr("newQuantity",10)) //숫자는이걸로
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete(base)
                .param("isbn","100000000001"))
                .andExpect(status().isOk());
    }
}
