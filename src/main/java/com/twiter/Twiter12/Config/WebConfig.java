package com.twiter.Twiter12.Config;

import com.twiter.Twiter12.Utils.GetIp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow all endpoints to be accessed cross-origin
                        .allowedOrigins("http://"+ GetIp.getIpFront()+":8000")  // Update with your public IP
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Add allowed methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);  // Allow credentials (cookies, authorization headers, etc.)
            }
        };
    }
}
