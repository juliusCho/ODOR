package com.back.odor;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    public ServletInitializer() {
        System.out.println("RESTCONTROLLER!!!!!!!!");;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OdorApplication.class);
    }

}
