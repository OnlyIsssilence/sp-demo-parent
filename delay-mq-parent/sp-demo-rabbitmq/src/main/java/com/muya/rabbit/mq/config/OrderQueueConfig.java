package com.muya.rabbit.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 16:01
 * @Description:
 */
@Configuration
public class OrderQueueConfig {

    /**
     * 订单队列
     */
    public static final String ORDER_QUEUE = "woniu.order.queue";

    /**
     * 订单exchange
     */
    public static final String ORDER_EXCHANGE = "woniu.order.exchage";

    /**
     * 超时订单队列
     */
    public static final String ORDER_TIMEOUT_QUEUE = "woniu.order.timeout.queue";


    /**
     * 订单exchange
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE, true, false, null);
    }

    /**
     * 订单队列
     */
    @Bean
    public Queue orderQueue() {
        // 设置超时转发策略 超时后消息会通过x-dead-letter-exchange 转发到x-dead-letter-routing-key绑定的队列中
        Map<String, Object> arguments = new HashMap<>(2);
        arguments.put("x-dead-letter-exchange", ORDER_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", ORDER_TIMEOUT_QUEUE);
        Queue queue = new Queue(ORDER_QUEUE, true, false, false, arguments);
        return queue;
    }

    /**
     * 超时订单队列
     *
     * @return
     */
    @Bean
    public Queue orderTimeoutQueue() {
        Queue queue = new Queue(ORDER_TIMEOUT_QUEUE, true, false, false);
        return queue;
    }

    /**
     * 订单队列绑定exchange
     *
     * @return
     */
    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(ORDER_QUEUE);
    }


    /**
     * 超时订单队列绑定exchange
     *
     * @return
     */
    @Bean
    public Binding orderTimeoutQueueBinding() {
        return BindingBuilder.bind(orderTimeoutQueue()).to(orderExchange()).with(ORDER_TIMEOUT_QUEUE);
    }
}

