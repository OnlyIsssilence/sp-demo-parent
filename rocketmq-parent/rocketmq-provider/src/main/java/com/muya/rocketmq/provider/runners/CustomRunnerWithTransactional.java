package com.muya.rocketmq.provider.runners;

import com.muya.rocketmq.provider.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-18
 * @Time: 14:48
 * @Description:
 */
@Component
public class CustomRunnerWithTransactional implements CommandLineRunner {

    @Autowired
    private SenderService senderService;

    @Override
    public void run(String... args) throws Exception {
        // COMMIT_MESSAGE message
        senderService.sendTransactionalMsg("transactional-msg1", 1);
        // ROLLBACK_MESSAGE message
        senderService.sendTransactionalMsg("transactional-msg2", 2);
        // ROLLBACK_MESSAGE message
        senderService.sendTransactionalMsg("transactional-msg3", 3);
        // COMMIT_MESSAGE message
        senderService.sendTransactionalMsg("transactional-msg4", 4);
    }

}
