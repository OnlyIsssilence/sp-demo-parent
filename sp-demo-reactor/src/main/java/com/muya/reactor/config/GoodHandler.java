package com.muya.reactor.config;

import com.muya.reactor.entity.Good;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-06-21
 * @Time: 23:07
 * @Description:
 */
@Component
@Configuration
public class GoodHandler {
    private final Flux<Good> goods;

    public GoodHandler(GoodGenerator goodGenerator) {
        this.goods = goodGenerator.findGoods();
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ok().contentType(TEXT_PLAIN).body(BodyInserters.fromObject("Hello Spring!"));
    }

    public Mono<ServerResponse> echo(ServerRequest request) {
        return ok().contentType(APPLICATION_STREAM_JSON)
                .body(this.goods, Good.class);
    }
}
