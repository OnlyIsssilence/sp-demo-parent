package com.muya.sp.demo.aop.controller;

import com.muya.sp.demo.aop.entity.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-12-16
 * @Time: 13:47
 * @Description:
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @ResponseBody
    @RequestMapping("test")
    public String test(HttpServletRequest request) {
        return UUID.randomUUID().toString();
    }

    @ResponseBody
    @RequestMapping("hello")
    public String hello(HttpServletRequest request, @RequestParam String name, @RequestBody UserVo userVo) {

        return UUID.randomUUID().toString();
    }
}
