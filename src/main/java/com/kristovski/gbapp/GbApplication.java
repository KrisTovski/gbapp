package com.kristovski.gbapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GbApplication {

    private static Logger logger = LogManager.getLogger(GbApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GbApplication.class, args);

        logger.info("Hello!");

    }

}
