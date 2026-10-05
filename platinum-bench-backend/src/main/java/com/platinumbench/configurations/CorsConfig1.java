package com.platinumbench.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig1{
    @Bean
    public WebMvcConfigurer configurer(){

        return new WebMvcConfigurer(){

            private void addCorsMapping(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173, https://platinumbench.com")
                        .allowedMethods("GET","POST","UPDATE","DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

}