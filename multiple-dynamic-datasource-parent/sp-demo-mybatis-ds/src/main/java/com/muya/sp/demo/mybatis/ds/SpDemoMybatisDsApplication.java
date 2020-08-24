package com.muya.sp.demo.mybatis.ds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 15:16
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.muya.sp.demo")
public class SpDemoMybatisDsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpDemoMybatisDsApplication.class, args);
    }

}
