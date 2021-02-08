package com.muya.sp.demo.tables;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-02-08
 * @Time: 14:47
 * @Description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.muya.sp.demo.tables.mapper"})
public class SpDemoTablesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpDemoTablesApplication.class, args);
    }

}
