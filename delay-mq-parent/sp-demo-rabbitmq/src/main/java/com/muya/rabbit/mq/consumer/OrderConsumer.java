package com.muya.rabbit.mq.consumer;

import com.muya.rabbit.mq.config.OrderQueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 16:10
 * @Description:
 */
@Service
public class OrderConsumer {
    @RabbitHandler
    @RabbitListener(queues = OrderQueueConfig.ORDER_TIMEOUT_QUEUE, concurrency = "4-10")
    public void consumeTimeOutQueue(@Payload String orderId) {
        System.out.println("接收到消息的时间" + System.currentTimeMillis());
        System.out.println("消息ID=" + orderId);
    }
}

