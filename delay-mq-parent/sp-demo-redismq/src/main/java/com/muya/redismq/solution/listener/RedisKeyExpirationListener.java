package com.muya.redismq.solution.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;


/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-14
 * @Time: 11:02
 * @Description: 类似于MQ的消息监听
 */

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private RedisTemplate redisTemplate;


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();

        redisTemplate.opsForValue().get(expiredKey);
        System.out.println("监听到key：" + expiredKey + "已过期");
    }
}

