package com.back.odor.common.session.controller;

import com.back.odor.common.session.service.SessionService;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.Mockito.when;
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

    @MockBean
    private SessionService sessionService;



    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("choinhyo")
    public void testValidateLogin() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUserId("tmp");
        userVO.setPassword("111");

        when(sessionService.validateLogin(userVO)).thenReturn("no");

        mockMvc.perform(
                post("/validateLogin", userVO)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)
        )
                .andExpect(status().isOk())
//                .andExpect(content().string("ok"))
//                .andExpect(content().contentType(MediaType.ALL))
//                .andExpect((ResultMatcher) jsonPath("$.id", "juilus"))
                .andDo(print());
    }
}
