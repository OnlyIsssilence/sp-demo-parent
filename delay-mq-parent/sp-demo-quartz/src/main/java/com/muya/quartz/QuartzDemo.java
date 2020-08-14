package com.muya.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 10:45
 * @Description:
 */
@Component
public class QuartzDemo {
    //每隔五秒
    @Scheduled(cron = "0/5 * * * * ? ")
    public void process() {
        System.out.println("我是定时任务！");
    }
}
