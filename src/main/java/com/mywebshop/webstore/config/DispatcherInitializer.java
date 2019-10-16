package com.mywebshop.webstore.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootAppContextDBConnectionConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppContext.class, ViewTemplateEngine.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
