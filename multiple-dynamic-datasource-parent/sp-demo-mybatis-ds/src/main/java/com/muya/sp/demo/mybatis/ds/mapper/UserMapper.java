package com.muya.sp.demo.mybatis.ds.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muya.sp.demo.mybatis.ds.entity.UserEntity;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 15:22
 * @Description:
 */
@DS("master")
public interface UserMapper extends BaseMapper<UserEntity> {
}
