package ru.quazar.l05springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(value = "name")
public class AppConfig {

    @Value( "${findString}" )
    private String findString;

    @Value( "${input_file}" )
    private String inputFileName;

    @Value( "${output_file}" )
    private String outputFileName;

    private final Map<String, String> configMapping = new HashMap<>();

    public Map<String, String> initEnvProperties() {

        configMapping.put("findString", findString );
        configMapping.put("input_file", inputFileName);
        configMapping.put("output_file", outputFileName);

        return configMapping;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "findString='" + findString + '\'' +
                ", inputFileName='" + inputFileName + '\'' +
                ", outputFileName='" + outputFileName + '\'' +
                '}';
    }
}
