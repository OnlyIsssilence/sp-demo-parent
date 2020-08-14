package com.muya.delay.queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 10:33
 * @Description:
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Order Order1 = new Order("Order1", 5, TimeUnit.SECONDS);
        Order Order2 = new Order("Order2", 10, TimeUnit.SECONDS);
        Order Order3 = new Order("Order3", 15, TimeUnit.SECONDS);
        DelayQueue<Order> delayQueue = new DelayQueue<>();
        delayQueue.put(Order1);
        delayQueue.put(Order2);
        delayQueue.put(Order3);

        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        while (delayQueue.size() != 0) {
            /**
             * 取队列头部元素是否过期
             */
            Order task = delayQueue.poll();
            if (task != null) {
                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            Thread.sleep(1000);
        }
    }

}
