package com.muya.rabbit.mq.consumer;

import com.muya.rabbit.mq.config.OrderQueueConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 16:10
 * @Description:
 */
@Service
@Slf4j
public class OrderConsumer {

    @RabbitHandler
    @RabbitListener(queues = OrderQueueConfig.ORDER_TIMEOUT_QUEUE, concurrency = "4-10")
    public void processHandler(String msg, Channel channel, Message message) throws IOException {

        try {
            log.info("消费者收到消息：{}", msg);
            //TODO 具体业务
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                // 拒绝消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}

