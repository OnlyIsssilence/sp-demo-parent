package com.muya.demo.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class SpDemoSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpDemoSecurityApplication.class, args);
    }

}
