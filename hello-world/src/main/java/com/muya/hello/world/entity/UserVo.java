package com.muya.hello.world.entity;

import lombok.Data;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-03-15
 * @Time: 10:25
 * @Description:
 */
@Data
public class UserVo {
    private String name;

    private Long age;

    private Double salary;
}
