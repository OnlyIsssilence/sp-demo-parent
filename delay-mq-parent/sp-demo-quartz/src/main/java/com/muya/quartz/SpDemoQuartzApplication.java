package com.muya.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpDemoQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpDemoQuartzApplication.class, args);
    }

}
