package com.back.odor.common.utils.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
//        xmlWebApplicationContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

        AnnotationConfigWebApplicationContext javaConfigContext = new AnnotationConfigWebApplicationContext();
        javaConfigContext.register(DispatcherConfig.class);
//        javaConfigContext.setConfigLocation("com.back.odor.common.utils.config");

        servletContext.addListener(new ContextLoaderListener(javaConfigContext));

        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("dispatcher", new DispatcherServlet(javaConfigContext));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
