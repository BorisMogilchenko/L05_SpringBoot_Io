package ru.quazar.l05springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ComponentScan({"ru.quazar.l05springboot"})
public class AppConfig {

    @Bean
    public ConfigProperties configPropertiesInit() {
        return new ConfigProperties();
    }

}