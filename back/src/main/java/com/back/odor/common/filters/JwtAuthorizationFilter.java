package com.back.odor.common.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.back.odor.common.etc.SecuredPropertySource;
import com.back.odor.common.session.service.SessionService;
import com.back.odor.menu.system.usermgmt.vo.BlockedUserVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import com.sun.security.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final SecuredPropertySource securedPropertySource;
    private final SessionService sessionService;


    public JwtAuthorizationFilter(
            AuthenticationManager authenticationManager,
            SecuredPropertySource securedPropertySource,
            SessionService sessionService
    ) {
        super(authenticationManager);
        this.securedPropertySource = securedPropertySource;
        this.sessionService = sessionService;
    }

    // endpoint every request hit with authorization
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Read the authorization header, where JWT token should be
        String header = request.getHeader(securedPropertySource.getHeaderString());

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(securedPropertySource.getTokenPrefix())) {
            // rest of the spring pipeline
            chain.doFilter(request, response);
            return;
        }

        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(securedPropertySource.getHeaderString());

        if (token != null) {
            // parse the token and validate it
            String userId = JWT.require(Algorithm.HMAC512(securedPropertySource.getClientSecret()))
                    .build()
                    .verify(token.replace(securedPropertySource.getTokenPrefix(), ""))
                    .getSubject();
            UserVO tmp = new UserVO();
            tmp.setUserId(userId);

            Object obj = sessionService.validateLogin(tmp);
            if (obj instanceof BlockedUserVO) return null;

            // Search in the DB if we find the user by token subject (userId)
            // If so, then grab user details and create spring auth token using userId, pass, authorities/rols
            if (obj instanceof UserVO) {
                UserVO user = (UserVO) obj;
                UserPrincipal principal = new UserPrincipal(user.getUserId());

                List<SimpleGrantedAuthority> authList = new ArrayList<>();
                authList.add(new SimpleGrantedAuthority("1"));

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, user.getPassword(), authList);
                return auth;
            }
        }
        return null;
    }

}
