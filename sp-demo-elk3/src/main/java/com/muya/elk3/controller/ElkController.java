package com.muya.elk3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-07-02
 * @Time: 10:48
 * @Description:
 */
@RestController
@Slf4j
public class ElkController {

    @Autowired
    private RestTemplate restTemplete;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/elkdemo")
    public String helloWorld() {
        String response = "Hello user ! " + new Date();
        log.info("/elkdemo - > " + response);

        return response;
    }

    @RequestMapping(value = "/exception")
    public String exception() {
        String rsp = "";
        try {
            int i = 1 / 0;
            // should get exception
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            log.error("Exception As String :: - > " + sStackTrace);

            rsp = sStackTrace;
        }

        return rsp;
    }

    @RequestMapping(value = "/elk")
    public String helloWorld1() {

        String response = restTemplete.exchange("http://localhost:8080/elkdemo", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        }).getBody();
        log.info("/elk - > " + response);

        try {
            String exceptionrsp = restTemplete.exchange("http://localhost:8080/exception", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
            }).getBody();
            log.info("/elk trying to print exception - > " + exceptionrsp);
            response = response + " === " + exceptionrsp;
        } catch (Exception e) {
            // exception should not reach here. Really bad practice :)
        }

        return response;
    }

    @GetMapping("test")
    public void test() {
        log.info("88888888**************88888********测试初始一些日志吧！8083");
    }

}

