package com.devjr.apiJWT.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${path-img}")
    public String pathImage;

    public void addResourceHandler(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+pathImage);
    }
}
