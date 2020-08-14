package com.muya.redismq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 13:36
 * @Description:
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping(value = "test", method = {RequestMethod.GET, RequestMethod.POST})
    public String redisTest() {

        redisTemplate.opsForValue().set("hello123","world456");
        System.out.println(redisTemplate.opsForValue().get("xiaoming"));
        return UUID.randomUUID().toString();

    }


}
