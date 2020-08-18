package com.muya.rocketmq.provider.runners;

import com.muya.rocketmq.provider.entity.Foo;
import com.muya.rocketmq.provider.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-18
 * @Time: 15:06
 * @Description:
 */
@Component
public class OutPut1CustomRunner implements CommandLineRunner {

    @Autowired
    private SenderService senderService;

    @Override
    public void run(String... args) throws Exception {
        int count = 5;
        for (int index = 1; index <= count; index++) {
            String msgContent = "msg-" + index;
            if (index % 3 == 0) {
                senderService.send(msgContent);
            } else if (index % 3 == 1) {
                senderService.sendWithTags(msgContent, "tagStr");
            } else {
                senderService.sendObject(new Foo(index, "foo"), "tagObj");
            }
        }
    }

}
