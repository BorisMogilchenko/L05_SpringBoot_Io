package ru.quazar.l05springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "ru.quazar")
@PropertySource(value = "application.properties")
public class RepositoryConfiguration {

    @Bean
    public H2Configurer databaseConfig() {
        return new H2Configurer();
    }
}