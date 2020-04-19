package com.back.odor.common.session.controller;

import com.back.odor.common.etc.SecuredPropertySource;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    SecuredPropertySource securedPropertySource;

    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("ServiceController : validateLogin()")
    public void testValidateLogin() throws Exception {
        String user = "{\"userId\":\"" +
                securedPropertySource.getUserId() +
                "\",\"password\":\"" +
                securedPropertySource.getPassword() + "\"}";

        mockMvc.perform(
                post("/rest/validateLogin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user)
        )
                .andExpect(status().isOk())
//                .andExpect(content().string("ok"))
                ;
    }

    @Test
    public void sessionCheckTest() throws Exception {
        String sessionToken = "{\"sessionToken\":\"XX\"}";

        mockMvc.perform(
                post("/rest/sessionCheck")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sessionToken)
        ).andExpect(status().isOk());
    }
}
