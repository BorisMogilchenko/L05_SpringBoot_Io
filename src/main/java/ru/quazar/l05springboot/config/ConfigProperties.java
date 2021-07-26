package ru.quazar.l05springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource(value = "classpath:application.yml")
public class ConfigProperties {

    @Value( "${findString}" )
    private String findString;
    @Value( "${inFileName}" )
    private String inFileName;
    @Value( "${outFileName}" )
    private String outFileName;

    public String getFindString( ) {
        return findString;
    }

    public void setFindString(String findString) {
        this.findString = findString;
    }

    public String getInFileName( ) {
        return inFileName;
    }

    public void setInFileName(String inFileName) {
        this.inFileName = inFileName;
    }

    public String getOutFileName( ) {
        return outFileName;
    }

    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }
}
