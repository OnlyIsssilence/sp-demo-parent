package com.muya.sp.demo.tables.service;

import com.muya.sp.demo.tables.entity.User;
import com.muya.sp.demo.tables.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-02-08
 * @Time: 14:51
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void save(User user) {
        userMapper.insert(user);
    }
}
