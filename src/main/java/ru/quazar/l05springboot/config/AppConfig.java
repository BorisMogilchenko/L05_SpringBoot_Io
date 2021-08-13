package ru.quazar.l05springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ComponentScan({"ru.quazar.l05springboot"})
@ConfigurationProperties()
//@PropertySource(value = "classpath:application.yml")
public class AppConfig {

//    @NotBlank
    @Value( "${findString}" )
    private String findString;

//    @NotBlank
    @Value( "${infilename}" )
    private String inFileName;

//    @NotBlank
    @Value( "${outfilename}" )
    private String outFileName;

    public String getFindString( ) {
        return findString;
    }

    public String getInFileName( ) {
        return inFileName;
    }

    public String getOutFileName( ) {
        return outFileName;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "findString='" + findString + '\'' +
                ", inFileName='" + inFileName + '\'' +
                ", outFileName='" + outFileName + '\'' +
                '}';
    }
}
