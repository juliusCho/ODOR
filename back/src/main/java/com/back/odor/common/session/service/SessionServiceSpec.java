package com.back.odor.common.session.service;

import com.back.odor.common.session.mapper.SessionMapper;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

public interface SessionServiceSpec {

    // 상수 : 인터페이스에서 값을 정해줄테니 함부로 바꾸지 말고 제공해주는 값만 참조해라 (절대적)
    SessionMapper sessionMapper = null;
    Logger log = LoggerFactory.getLogger(SessionServiceSpec.class);

    // 추상 메소드 : 가이드만 줄테니 추상메소드를 오버라이팅해서 재구현해라. (강제적)
    Object validateLogin(UserVO user);

    // 디폴트 메소드 : 인터페이스에서 기본적으로 제공해주지만, 맘에 안들면 각자 구현해서 써라. (선택적)
    default UserVO validateLoginTest(UserVO user) {
//        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return sessionMapper.validateLogin(user);
    }

    // 정적 메소드 : 인터페이스에서 제공해주는 것으로 무조건 사용 (절대적)
    static void loginTrialConsole(UserVO user) {
        log.warn("Login Trial : " + user.toString());
    }


    Object getSession(HttpServletRequest req);
    boolean sessionCheck(UserVO user, HttpServletRequest req);
}
