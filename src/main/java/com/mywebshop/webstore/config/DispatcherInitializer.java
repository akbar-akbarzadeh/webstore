package com.mywebshop.webstore.config;

import com.mywebshop.webstore.config.db.RootAppContextDataSourceConfiguration;
import com.mywebshop.webstore.config.template.ViewTemplateEngine;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootAppContextDataSourceConfiguration.class};
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
