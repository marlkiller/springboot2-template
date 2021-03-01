package com.bespin.wzu3;

import com.bespin.wzu3.config.security.JwtTokenFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class Wzu3ApplicationTests  {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    private String token;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())  // 这里配置Security认证
                .build();
    }

    @Test
    public void unauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void login() throws Exception {
        // login and get Authorization
        mockMvc.perform(MockMvcRequestBuilders.get("/am/login")
                .param("username","admin")
                .param("password","363636")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void authorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello")
                .header(JwtTokenFilter.HEADER_STRING,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDIyOTgyM30.PZsAagqOKh7fvWNuZOgQwWznry64ksOoxlSDuO9zRpUhGdMVjah8ZWrJInNZZ5j4EPbUJvQ63OucSuK1-K_Umw")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
