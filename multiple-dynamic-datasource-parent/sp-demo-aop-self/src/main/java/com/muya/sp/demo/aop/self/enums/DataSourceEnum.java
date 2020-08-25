package com.muya.sp.demo.aop.self.enums;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 22:38
 * @Description:
 */
public enum DataSourceEnum {
    DB1("db1"), DB2("db2");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
