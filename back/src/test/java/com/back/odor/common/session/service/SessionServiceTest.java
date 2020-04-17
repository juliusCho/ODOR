package com.back.odor.common.session.service;

import com.back.odor.common.etc.SecuredPropertySource;
import com.back.odor.common.session.mapper.SessionMapper;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SessionServiceTest implements SessionServiceSpec {

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private SecuredPropertySource securedPropertySource;


    @Test
    public void validateLoginTest() {
    }

    @Override
    public String validateLogin(UserVO user) {
        user.setUserId(securedPropertySource.getUserId());
        user.setPassword(securedPropertySource.getPassword());
        String result = String.valueOf(sessionMapper.validateLogin(user));
        log.debug("validateLogin RESULT : " + result);
        return result;
    }

    @Override
    public Integer validateLoginTest(UserVO user) {
        user.setUserId(securedPropertySource.getUserId());
        user.setPassword(securedPropertySource.getPassword());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
//        assert sessionMapper.validateLogin(user).equals(0);
        log.error(String.valueOf(sessionMapper.validateLogin(user)));

        return null;
    }
}
