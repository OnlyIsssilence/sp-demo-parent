package com.muya.reactor.config;

import com.muya.reactor.entity.Good;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-06-21
 * @Time: 23:05
 * @Description:
 */
@Configuration
public class GoodGenerator {

    public Flux<Good> findGoods() {
        List<Good> goods = new ArrayList<>();
        goods.add(new Good(1, "小米", "2000"));
        goods.add(new Good(2, "华为", "4000"));
        goods.add(new Good(3, "苹果", "8000"));
        return Flux.fromIterable(goods);
    }
}
