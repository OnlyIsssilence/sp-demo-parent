package com.muya.sp.demo.aop.self.mapper.db1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muya.sp.demo.aop.self.annotation.DataSource;
import com.muya.sp.demo.aop.self.entity.UserEntity;
import com.muya.sp.demo.aop.self.enums.DataSourceEnum;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 15:22
 * @Description:
 */
@DataSource(DataSourceEnum.DB1)
public interface UserMapper extends BaseMapper<UserEntity> {


}
