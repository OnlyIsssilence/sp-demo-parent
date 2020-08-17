package com.muya.netty.mq;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-17
 * @Time: 16:47
 * @Description:
 */
public class NettyDelayQueue {

    public static void main(String[] args) {

        final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);

        //定时任务
        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order1  5s 后执行 ");
                timer.newTimeout(this, 5, TimeUnit.SECONDS);//结束时候再次注册
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);
        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order2  10s 后执行");
                timer.newTimeout(this, 10, TimeUnit.SECONDS);//结束时候再注册
            }
        };

        timer.newTimeout(task2, 10, TimeUnit.SECONDS);

        //延迟任务
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order3  15s 后执行一次");
            }
        }, 15, TimeUnit.SECONDS);

    }
}

