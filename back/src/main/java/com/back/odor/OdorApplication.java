package com.back.odor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class OdorApplication {

    public static void main(String[] args) {
//      **
//      * SpringApplication application = new SpringApplication(OdorApplication.class);
//      * application.run(args);
//      **
        SpringApplication.run(OdorApplication.class, args);
    }


}
