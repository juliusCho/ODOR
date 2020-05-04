package com.back.odor.common.utils.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
//                .antMatchers("/odor/**/system").access("hasRole('10')")
                .antMatchers("/odor/**/system").permitAll()
//                .antMatchers("/odor/**/api").access("hasAnyRole('1', '10')")
                .antMatchers("/odor/**/api").permitAll()
                .antMatchers("/odor/**/rest").permitAll()
                .anyRequest()
                    .authenticated()
                        .and()
                .formLogin()
                    .loginPage("/invalid")
                    .loginProcessingUrl("/odor/invalid/rest")
                    .failureUrl("/odor/invalid/rest?error")
                        .and()
                .httpBasic()
                        .and()
            .exceptionHandling()
                .accessDeniedHandler((request, response, exception) -> {
                    if (exception instanceof MissingCsrfTokenException) {
                        log.error("SESSION EXPIRED");
                        log.error(exception.toString());
                    } else if (exception instanceof InvalidCsrfTokenException) {
                        log.error("INVALID TOKEN");
                        log.error(exception.toString());
                    }
                })
                        .and()
            .csrf()
                .disable()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

}
