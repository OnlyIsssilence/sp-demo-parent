package com.muya.reactor.controller;

import com.muya.reactor.entity.Foobar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

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

        WebClient webClient = WebClient.create("http://localhost:8080");

        return Mono.just(new Foobar());
    }

    @GetMapping(value = "/test")
    public String test() {
        return UUID.randomUUID().toString();
    }
}
