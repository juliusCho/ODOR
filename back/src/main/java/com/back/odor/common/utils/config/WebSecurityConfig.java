package com.back.odor.common.utils.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableAuthorizationServer
@Slf4j
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter
        implements AuthorizationServerConfigurer {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/odor/**/system").access("hasRoles('USER', 'ADMIN')")
//                .antMatchers("/odor/**/api").hasRole("USER")
                .antMatchers("/odor/**/api").permitAll()
                .antMatchers("/odor/**/rest").permitAll()
                .anyRequest().authenticated()
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
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }


    @Bean
    public TokenStore jdbcTokenStore(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {

    }
}
