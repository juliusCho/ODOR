package com.back.odor.common.utils.vo;

import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class MyAuthentication extends UsernamePasswordAuthenticationToken implements Serializable {

    private static final long serialVersionUID = 6306006384692830910L;

    UserVO user;

    public MyAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UserVO user) {
        super(principal, credentials, authorities);
        this.user = user;
    }

}
