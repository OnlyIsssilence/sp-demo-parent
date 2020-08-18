package com.muya.rocketmq.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-18
 * @Time: 11:39
 * @Description:
 */
@SpringBootApplication
@EnableBinding({MySource.class})
public class RocketmqProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqProviderApplication.class, args);
    }

}
