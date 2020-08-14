package com.muya.delay.queue;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 10:30
 * @Description:
 */
public class Order implements Delayed {
    /**
     * 延迟时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private long time;
    String name;

    public Order(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Order Order = (Order) o;
        long diff = this.time - Order.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
