package com.muya.sp.demo.graceful.close;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-01-22
 * @Time: 10:41
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class SpDemoGracefulCloseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpDemoGracefulCloseApplication.class, args);
    }

}
