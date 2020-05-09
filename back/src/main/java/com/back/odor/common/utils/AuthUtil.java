package com.back.odor.common.utils;

import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class AuthUtil {

    public static UserVO getCurrentUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = servletRequestAttributes.getRequest().getSession(true);
        return (UserVO) httpSession.getAttribute("user");
    }

    public static String getCurrentUserId() {
        UserVO vo = getCurrentUser();
        if (vo == null) {
            return "";
        } else {
            return vo.getUserId();
        }
    }

}
