package com.muya.sp.demo.aop.controller.another;

import com.muya.sp.demo.aop.entity.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/another")
@Slf4j
public class AnotherController {

    @ResponseBody
    @RequestMapping("test")
    public String test() {
        return UUID.randomUUID().toString();
    }

    @ResponseBody
    @RequestMapping("hello")
    public String hello(@RequestParam String name, @RequestBody UserVo userVo) {

        return UUID.randomUUID().toString();
    }
}
