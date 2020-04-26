package com.back.odor.common.session.service;

import com.back.odor.common.session.mapper.SessionMapper;
import com.back.odor.menu.system.usermgmt.vo.BlockedUserVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
@Slf4j
public class SessionService implements SessionServiceSpec {

    @Autowired
    private SessionMapper sessionMapper;


    @Override
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

    @Override
    public Object getSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return session.getAttribute("user");
    }

    @Override
    public boolean sessionCheck(UserVO user, HttpServletRequest req) {
        Object obj = this.getSession(req);
        if (!(obj instanceof UserVO)) {
            return false;
        }
        UserVO session = (UserVO) obj;

        if (!user.equals(session)) {
            return false;
        }
        return true;
    }

}
