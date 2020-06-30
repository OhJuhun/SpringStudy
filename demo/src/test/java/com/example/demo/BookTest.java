package com.example.demo;

import com.example.demo.documentation.BookDocumentation;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import java.nio.charset.StandardCharsets;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    BookService bookService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new ShallowEtagHeaderFilter())
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .apply(documentationConfiguration(restDocumentation).operationPreprocessors().withResponseDefaults(prettyPrint()))
                .build();
    }


    //Method 단위 테스트
    @Test
    void getAll() throws Exception{
        this.mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(BookDocumentation.getBooks());
    }

    @Test
    void insertBook() throws Exception{
        this.mockMvc.perform(post("/book")
                .contentType("application/json;charset=UTF-8")
                .content("{\"name\":\"Java 9 in action\",\"isbn\":\"100000005555\",\"quantity\":6}"))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(BookDocumentation.insertBook());
    }

    @Test
    void getBookByIsbn() throws Exception{
        this.mockMvc.perform(get("/book/123412341234"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("JPA Using")))
                .andDo(print())
                .andDo(BookDocumentation.getBookByIsbn());
    }

    @Test
    void deleteBook() throws Exception{
        this.mockMvc.perform(delete("/book/3"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(BookDocumentation.deleteBook());
    }

    @Test
    void modifyBook() throws Exception{
        this.mockMvc.perform(put("/book/14")
                .contentType("application/json;charset=UTF-8")
                .content("{\"name\":\"Your Book\",\"isbn\":\"171717711717\",\"quantity\":8}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(BookDocumentation.modifyBook());
    }

}
