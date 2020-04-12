package com.back.odor.common.session.service;

import com.back.odor.common.session.mapper.SessionMapper;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionServiceTest {

    @Autowired
    private SessionMapper sessionMapper;

    @Test
    public void validateLoginTest() {
        UserVO user = new UserVO();
        user.setUserId("julius");
        user.setPassword("juliuslovesrenee");
        System.out.println(sessionMapper.validateLogin(user));
    }
}
