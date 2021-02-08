package com.muya.sp.demo.tables.config;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-02-08
 * @Time: 14:43
 * @Description: 分库策略
 */
public class MemberIdShardingSchemeAlgorithm implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        for (String str : availableTargetNames) {
            int index = shardingValue.getValue() % 100;
            return str + (index > 49 ? "2" : "1");
        }
        return null;
    }

}
