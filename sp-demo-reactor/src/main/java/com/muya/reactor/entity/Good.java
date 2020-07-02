package com.muya.reactor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-06-21
 * @Time: 23:05
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    private int id;
    private String name;
    private String price;
}
