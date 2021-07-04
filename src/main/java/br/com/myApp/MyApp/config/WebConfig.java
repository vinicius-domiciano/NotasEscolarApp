package br.com.myApp.MyApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class WebConfig {

    private static final String[] BASE_URL = { "*" };
    private static final String[] METHODS_PERMISSION = {
            "GET", "POST", "PUT", "DELETE"
    };

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/escola/**")
                        .allowedOrigins(BASE_URL)
                        .allowedMethods(METHODS_PERMISSION);
            }
        };
    }

}
