package com.back.odor.common.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class OAuth2ResouceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .anyRequest().permitAll()
//                .antMatchers("/api/**").authenticated()
                .antMatchers("/odor/**").
                access("#oauth2.hasScope('read')");
    }
}
