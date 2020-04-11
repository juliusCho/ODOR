package com.back.odor.common.session.service;

import com.back.odor.common.session.mapper.SessionMapper;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SessionService implements SessionServiceSpec {

    @Resource
    SessionMapper sessionMapper;

    public String validateLogin(UserVO user) {
        if (sessionMapper.validateLogin(user) > 0) {
            return "ok";
        } else return "no";
    }
}
