package com.muya.sp.demo.mybatis.ds.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 15:19
 * @Description:
 */
@Data
@TableName("ds_user")
public class UserEntity {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long id;
    private String name;
    private Integer age;
}
