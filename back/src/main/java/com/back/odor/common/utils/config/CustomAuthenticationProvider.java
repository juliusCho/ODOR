package com.back.odor.common.utils.config;

import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static List<UserVO> users = new ArrayList<>();

    public CustomAuthenticationProvider() {
        users.add(new UserVO("julius", "123", "USER"));
        users.add(new UserVO("renee", "123", "ADMIN"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        System.out.println("credentials class: " + credentials.getClass());
        if (!(credentials instanceof String)) {
            return null;
        }
        String password = credentials.toString();

        Optional<UserVO> userOptional = users.stream()
                                            .filter(user -> user.match(name, password))
                                            .findFirst();
        if (!userOptional.isPresent()) {
            throw new BadCredentialsException("Authentication failed for " + name);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userOptional.get().getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
