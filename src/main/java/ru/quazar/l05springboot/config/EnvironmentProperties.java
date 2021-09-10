package ru.quazar.l05springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()
public class EnvironmentProperties {

    @Autowired
    private Environment env;

    public String getFindString( ) {
        return env.getProperty("findString");
    }

    public String getInFileName( ) {
        return env.getProperty("inputfilename");
    }

    public String getOutFileName( ) {
        return env.getProperty("outputfilename");
    }

}
