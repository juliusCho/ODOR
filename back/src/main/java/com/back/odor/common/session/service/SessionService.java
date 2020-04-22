package com.back.odor.common.session.service;

import com.back.odor.common.session.mapper.SessionMapper;
import com.back.odor.menu.system.usermgmt.vo.BlockedUserVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionService implements SessionServiceSpec {

    @Autowired
    private SessionMapper sessionMapper;

    public Object validateLogin(UserVO user) {
        SessionServiceSpec.loginTrialConsole(user);
        UserVO loginUser = sessionMapper.validateLogin(user);
        Object resultUser = loginUser;

        if (loginUser != null) {
            BlockedUserVO blockedUserVO = sessionMapper.checkBlockedUser(loginUser.getEmail());
            if (blockedUserVO != null) {
                resultUser = blockedUserVO;
            }
        }
        return resultUser;
    }
}
