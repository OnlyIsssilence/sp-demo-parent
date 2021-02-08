package com.muya.sp.demo.tables.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-02-08
 * @Time: 14:45
 * @Description:
 */
@TableName("t_user")
@Data
public class User {

    @TableId(type = IdType.INPUT)
    private Integer id;
    private String name;
    private Integer age;
}
