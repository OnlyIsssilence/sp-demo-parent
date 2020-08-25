package com.muya.sp.demo.aop.self.mapper.db2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muya.sp.demo.aop.self.annotation.DataSource;
import com.muya.sp.demo.aop.self.entity.BookEntity;
import com.muya.sp.demo.aop.self.entity.UserEntity;
import com.muya.sp.demo.aop.self.enums.DataSourceEnum;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 15:22
 * @Description:
 */
@DataSource(DataSourceEnum.DB2)
public interface BookMapper extends BaseMapper<BookEntity> {
    @Select("select * from ds_book")
    @DataSource(DataSourceEnum.DB2)
    List<UserEntity> getTotal();
}
