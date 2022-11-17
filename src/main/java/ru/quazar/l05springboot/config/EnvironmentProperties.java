package ru.quazar.l05springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "name")
public class EnvironmentProperties {

//    @Autowired
    private Environment env;

    public EnvironmentProperties(Environment env) {
        this.env = env;
    }

    public String getFindString( ) {
        return env.getProperty("findString");
    }

    public String getInFileName( ) {
        return env.getProperty("input-file");
    }

    public String getOutFileName( ) {
        return env.getProperty("output-file");
    }

    @Override
    public String toString() {
        return "EnvironmentProperties{" +
                "env=" + env +
                '}';
    }

}
