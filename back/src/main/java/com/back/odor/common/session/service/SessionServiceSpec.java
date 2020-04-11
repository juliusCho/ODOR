package com.back.odor.common.session.service;

import com.back.odor.menu.system.usermgmt.vo.UserVO;

public interface SessionServiceSpec {

    String validateLogin(UserVO user);
}
