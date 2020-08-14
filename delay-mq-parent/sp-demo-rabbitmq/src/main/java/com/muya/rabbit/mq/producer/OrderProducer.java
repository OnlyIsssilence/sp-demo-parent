package com.muya.rabbit.mq.producer;

import com.muya.rabbit.mq.config.OrderQueueConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 16:09
 * @Description:
 */
@Service
public class OrderProducer {

    @Autowired
    private AmqpTemplate template;

    public void sendOrder(String orderId) {
        template.convertAndSend(OrderQueueConfig.ORDER_EXCHANGE, OrderQueueConfig.ORDER_QUEUE, orderId, message -> {
            // 设置超时时间 3000ms
            message.getMessageProperties().setExpiration("3000");
            return message;
        });
    }
}

