package com.muya.rabbit.mq.controller;

import com.muya.rabbit.mq.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 16:14
 * @Description:
 */
@RestController
@RequestMapping("rabbit")
public class RabbitMqController {

    @Autowired
    private OrderProducer orderProducer;

    @ResponseBody
    @RequestMapping("sendMq")
    public String sendToMQ(@RequestParam(value = "name") String name) {
        String orderId = name + UUID.randomUUID().toString();
        orderProducer.sendOrder(orderId);
        return orderId;
    }
}
