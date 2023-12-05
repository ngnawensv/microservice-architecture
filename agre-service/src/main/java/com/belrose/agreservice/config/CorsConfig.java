package com.belrose.agreservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${omega.cors.allowed.origins}")
    private String[] allowedOrigins;
  @Override
 public void addCorsMappings(CorsRegistry registry){

   registry.addMapping("/**")
           .allowedOrigins(allowedOrigins)
           .allowedHeaders("*")
           .allowedMethods("*");
 }
}
