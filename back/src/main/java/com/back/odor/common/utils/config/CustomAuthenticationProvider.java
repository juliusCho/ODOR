package com.back.odor.common.utils.config;

import com.back.odor.common.utils.AuthUtil;
import com.back.odor.common.utils.vo.MyAuthentication;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        Object credentials = authentication.getCredentials();
        if (!(credentials instanceof String)) {
            return null;
        }
        String password = credentials.toString();
        return authenticate(userId, password);
    }

    private Authentication authenticate(String userId, String password) {
        UserVO user = AuthUtil.getUserByIdAndPw(userId, password);
        if (user == null) {
            log.error("Cannot find user with ID: {} / PW: {}", userId, password);
            return null;
        }
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("1"));
        return new MyAuthentication(userId, password, authList, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
