package com.muya.sp.demo.tables.controller;

import com.muya.sp.demo.tables.entity.User;
import com.muya.sp.demo.tables.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-02-08
 * @Time: 14:48
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("add")
    public String add(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "age") Integer age,
            @RequestParam(value = "name") String name) {
        User user = new User();
        user.setId(id);
        user.setAge(age);
        user.setName(name);
        userService.save(user);

        return UUID.randomUUID().toString();
    }
}
