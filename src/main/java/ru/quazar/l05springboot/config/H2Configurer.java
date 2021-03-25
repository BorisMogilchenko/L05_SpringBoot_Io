package ru.quazar.l05springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2Configurer {

    private static final Logger logger = LoggerFactory.getLogger(H2Configurer.class);

    public H2Configurer() {

        logger.info("Configuring H2 database");
    }
}