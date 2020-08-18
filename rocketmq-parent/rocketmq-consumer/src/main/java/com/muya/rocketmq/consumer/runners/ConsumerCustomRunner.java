package com.muya.rocketmq.consumer.runners;

import com.muya.rocketmq.consumer.MySink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-18
 * @Time: 14:23
 * @Description:
 */
@Component
public class ConsumerCustomRunner implements CommandLineRunner {

    @Autowired
    private MySink mySink;

    @Override
    public void run(String... args) throws InterruptedException {
        while (true) {
            mySink.input5().poll(m -> {
                String payload = (String) m.getPayload();
                System.out.println("pull msg: " + payload);
            }, new ParameterizedTypeReference<String>() {
            });
            Thread.sleep(2_000);
        }
    }
}
