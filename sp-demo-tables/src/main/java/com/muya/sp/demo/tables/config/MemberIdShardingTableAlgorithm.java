package com.muya.sp.demo.tables.config;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-02-08
 * @Time: 14:44
 * @Description: 分表策略，按照 id 分表
 */
public class MemberIdShardingTableAlgorithm implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        int index = shardingValue.getValue() % 100;
        return shardingValue.getLogicTableName() + "_" + (index < 10 ? "0" + index : index + "");
    }
}
