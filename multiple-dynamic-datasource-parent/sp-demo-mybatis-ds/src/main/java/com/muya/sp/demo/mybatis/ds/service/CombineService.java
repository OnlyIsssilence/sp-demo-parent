package com.muya.sp.demo.mybatis.ds.service;

import com.alibaba.fastjson.JSON;
import com.muya.sp.demo.mybatis.ds.mapper.BookMapper;
import com.muya.sp.demo.mybatis.ds.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 16:58
 * @Description:
 */
@Service
@Slf4j
public class CombineService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    public String getAll() {
        return JSON.toJSONString(userMapper.selectList(null).get(0))
                + "******" + JSON.toJSONString(bookMapper.selectList(null).get(0));
    }

}
