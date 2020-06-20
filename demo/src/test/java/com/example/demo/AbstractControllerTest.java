package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc //MockMvc Test
public abstract class AbstractControllerTest {
    //중복 제거를 위한 추상화
    @Autowired
    protected MockMvc mockMvc;

    abstract protected Object controller();

    @BeforeEach
    protected void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller())
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .alwaysDo(print()) //콘솔에 결과 출력
                .build();
    }
}
