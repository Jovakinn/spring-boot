package com.mainacad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        RedirectViewControllerRegistration r =
                registry.addRedirectViewController("/", "/authorization");
    }
}
