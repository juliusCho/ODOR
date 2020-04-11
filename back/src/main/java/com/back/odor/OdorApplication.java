package com.back.odor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OdorApplication {

    public static void main(String[] args) {
//      **
//      * SpringApplication application = new SpringApplication(OdorApplication.class);
//      * application.run(args);
//      **
        SpringApplication.run(OdorApplication.class, args);
    }

}
