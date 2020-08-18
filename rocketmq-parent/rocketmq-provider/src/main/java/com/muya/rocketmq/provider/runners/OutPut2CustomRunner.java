package com.muya.rocketmq.provider.runners;

import com.muya.rocketmq.provider.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-18
 * @Time: 15:07
 * @Description: 使用source->MessageChannel推送消息
 */
@Component
public class OutPut2CustomRunner implements CommandLineRunner {

    @Autowired
    private MySource mySource;

    @Override
    public void run(String... args) {
        int count = 20;
        for (int index = 1; index <= count; index++) {
            String msgContent = "pullMsg-" + index;
            mySource.output3()
                    .send(MessageBuilder.withPayload(msgContent).build());
        }
    }

}
