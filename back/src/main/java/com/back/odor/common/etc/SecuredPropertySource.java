package com.back.odor.common.etc;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(value = {
        @PropertySource(value = "file:C:/dev/workspace/secured.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:Users/yona/documents/odor-project/secured-properties", ignoreResourceNotFound = true)
})
@Getter
public class SecuredPropertySource {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.jdbc-url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${security.user.userId}")
    private String userId;
}
