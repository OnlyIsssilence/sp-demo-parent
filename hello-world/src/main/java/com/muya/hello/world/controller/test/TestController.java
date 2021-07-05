package com.muya.hello.world.controller.test;

import com.muya.hello.world.entity.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-01-13
 * @Time: 8:55
 * @Description:
 */
@RestController
@RequestMapping("test")
@Api(tags = "测试操作")
@Tag("")
public class TestController {

    @ResponseBody
    @PostMapping("hello")
    @ApiOperation("hello操作")
    public String hello(@RequestParam String companyName,
                        @RequestBody UserVo userVo) {
        return "hello" + UUID.randomUUID().toString();
    }


}
