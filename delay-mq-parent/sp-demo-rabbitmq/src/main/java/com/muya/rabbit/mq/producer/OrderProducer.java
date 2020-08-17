package com.muya.rabbit.mq.producer;

import com.muya.rabbit.mq.config.OrderQueueConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ConfirmCallbackService confirmCallbackService;

    @Autowired
    private ReturnCallbackService returnCallbackService;

    public void sendOrder(String orderId) {
        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
        rabbitTemplate.setMandatory(true);

        /**
         * 消费者确认收到消息后，手动ack回执回调处理
         */
        rabbitTemplate.setConfirmCallback(confirmCallbackService);

        /**
         * 消息投递到队列失败回调处理
         */
        rabbitTemplate.setReturnCallback(returnCallbackService);

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        System.out.println("correlationData id:" + correlationData.getId());

        rabbitTemplate.convertAndSend(OrderQueueConfig.ORDER_EXCHANGE, OrderQueueConfig.ORDER_QUEUE,
                orderId, message -> {
                    // 设置超时时间 3000ms
                    message.getMessageProperties().setExpiration("30000");
                    return message;
                }, correlationData);
    }
}

