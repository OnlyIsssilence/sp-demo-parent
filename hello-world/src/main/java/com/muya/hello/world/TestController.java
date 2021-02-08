package com.muya.hello.world;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class TestController {

    @ResponseBody
    @RequestMapping("hello")
    public String hello() {
        return "hello" + UUID.randomUUID().toString();
    }

    /**
     * 下载年度报告签名的excel文件
     *
     * @param userId
     * @param id     年度报告id
     * @return
     */
    @ResponseBody
    @RequestMapping("exportWorldYear")
    public void exportWorldYear(
            HttpServletResponse response,
            HttpServletRequest request) {
        WorldUtils.doc2pdf("test", response);

    }




}
