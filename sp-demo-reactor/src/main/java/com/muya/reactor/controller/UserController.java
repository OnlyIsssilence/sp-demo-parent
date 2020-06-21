package com.muya.reactor.controller;

import com.muya.reactor.entity.Foobar;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-06-21
 * @Time: 20:50
 * @Description:
 */
@RestController
public class UserController {

    @RequestMapping(value = "/foobar")
    public Mono<Foobar> foobar() {
        return Mono.just(new Foobar());
    }
}
