package com.muya.sp.demo.aop.self.annotation;

import com.muya.sp.demo.aop.self.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 22:39
 * @Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DataSourceEnum value() default DataSourceEnum.DB1;
}

