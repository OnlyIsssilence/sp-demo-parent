package com.muya.sp.demo.aop.service;

import com.muya.sp.demo.aop.entity.UserVo;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-12-16
 * @Time: 13:51
 * @Description:
 */
@Service
public class UserService {

    public UserVo getOne(String companyName) {
        UserVo userVo = new UserVo();
        userVo.setAge(new Random(100).nextInt());
        userVo.setCompanyName(companyName);
        userVo.setPrice(new Random(100).nextDouble());

        return userVo;
    }
}
