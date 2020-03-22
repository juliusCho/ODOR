package com.back.odor.common.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
            .and()
                .csrf()
            .and()
                .exceptionHandling()
                    .accessDeniedPage("/denied.html")
                    .accessDeniedHandler(new AccessDeniedHandler() {
                                @Override
                                public void handle(
                                        HttpServletRequest request,
                                        HttpServletResponse response,
                                        AccessDeniedException exception
                                ) throws IOException, ServletException {
                                    if (exception instanceof MissingCsrfTokenException) {
                                        // session expired
                                        System.out.println("세션끝났다!!!!");
                                    } else if (exception instanceof InvalidCsrfTokenException) {
                                        // invalid token
                                        System.out.println("토큰이상햄!!!!");
                                    }
                                }
                            })
            .and()
        ;
    }
}
