package com.muya.reactor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-06-21
 * @Time: 23:11
 * @Description:
 */
@Configuration
public class GoodRouter {
    @Bean
    public RouterFunction<ServerResponse> route(GoodHandler goodHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/good")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), goodHandler::hello)
                .andRoute(RequestPredicates.GET("/goods")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), goodHandler::echo);
    }
}
