package com.muya.sp.demo.graceful.close.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-01-11
 * @Time: 15:04
 * @Description:
 */
@RestController
@RequestMapping("/token")
@Slf4j
public class SleepController {

    @GetMapping("sleep")
    public String sleep(Integer timeout) {
        try {
            log.info("begin sleep:{}", timeout);
            TimeUnit.SECONDS.sleep(timeout);
            log.info("end sleep:{}", timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sleep:" + timeout;
    }

}
