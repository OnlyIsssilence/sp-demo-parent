package com.muya.sp.demo.graceful.close.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-01-11
 * @Time: 15:33
 * @Description: 有赞商城code/token管理定时任务
 */
@Component
@Slf4j
public class TokenManageTask {

    @Scheduled(cron = "*/27 * * * * ?")
    public void longTimeTask25() {
        log.info("longTimeTask25 begin!");
        long start = System.currentTimeMillis();

        try {
            TimeUnit.SECONDS.sleep(25);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("longTimeTask25 end cost:{}", System.currentTimeMillis() - start);
    }

    @Scheduled(cron = "*/30 * * * * ?")
    public void longTimeTask15() {
        log.info("longTimeTask15 begin!");
        long start = System.currentTimeMillis();

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("longTimeTask15 end cost:{}", System.currentTimeMillis() - start);
    }

}
