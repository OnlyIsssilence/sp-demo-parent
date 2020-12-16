package com.muya.fx.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-12-13
 * @Time: 23:28
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;
    private int age;
    private double score;

}
