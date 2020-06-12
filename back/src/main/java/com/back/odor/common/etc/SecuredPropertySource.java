package com.back.odor.common.etc;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.annotation.Order;

@Configuration
@PropertySources(value = {
        @PropertySource(value = "file:C:/dev/workspace/secured.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:/Users/yona/documents/odor-project/secured.properties", ignoreResourceNotFound = true)
})
@Getter
@Order(0)
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




    @Value("${client-secret}")
    private String clientSecret;

    @Value("${expiration-time}")
    private Long expirationTime;

    @Value("${token-prefix}")
    private String tokenPrefix;

    @Value("${header-string}")
    private String headerString;





    @Value("${file-path}")
    private String filePath;

    @Value("${file-user-id}")
    private String fileUserId;

    @Value("${file-user-pw}")
    private String fileUserPw;

    @Value("${file-tmp-path}")
    private String fileTmpPath;

}
