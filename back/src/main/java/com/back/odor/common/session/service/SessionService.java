package com.back.odor.common.session.service;

import com.back.odor.common.session.mapper.SessionMapper;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionService implements SessionServiceSpec {

    @Autowired
    private SessionMapper sessionMapper;

    public String validateLogin(UserVO user) {
        if (sessionMapper.validateLogin(user) > 0) {
            return "ok";
        } else return "no";
    }
}
