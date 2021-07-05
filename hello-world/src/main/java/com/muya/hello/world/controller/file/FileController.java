package com.muya.hello.world.controller.file;

import com.muya.hello.world.WorldUtils;
import com.muya.hello.world.entity.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@Api(tags = "文件操作")
public class FileController {
    /**
     * @return
     */
    @ApiOperation(value = "导入导出", notes = "备注")
    @ResponseBody
    @PostMapping("exportWorldYear")
    public void exportWorldYear(
            @RequestBody UserVo userVo,
            HttpServletResponse response,
            HttpServletRequest request) {
        WorldUtils.doc2pdf("test", response);

    }


}
