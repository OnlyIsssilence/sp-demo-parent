package com.muya.sp.demo.aop.self.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muya.sp.demo.aop.self.entity.UserEntity;
import com.muya.sp.demo.aop.self.mapper.db1.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 15:24
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> getAll() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time");
        return userMapper.selectList(queryWrapper);
    }
}
