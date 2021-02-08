package com.muya.sp.demo.tables.config;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2021-02-08
 * @Time: 14:41
 * @Description:
 */

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.Lists;
import io.shardingsphere.core.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Captain on 01/03/2019.
 */
@Configuration
@MapperScan(basePackages = {"com.muya.sp.demo.tables.mapper"}, sqlSessionFactoryRef = "userShardingSqlSessionFactory")
public class UserShardingDBConfiguration {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    private static final String USER_1_MASTER = "dsUser1Master";
    private static final String USER_1_SLAVE = "dsUser1Slave";
    private static final String USER_2_MASTER = "dsUser2Master";
    private static final String USER_2_SLAVE = "dsUser2Slave";
    private static final String USER_SHARDING_1 = "dsMasterSlave1";
    private static final String USER_SHARDING_2 = "dsMasterSlave2";

    private static final String USER_SHARDING_DATA_SOURCE = "userSharding";

    @Bean(USER_1_MASTER)
    @ConfigurationProperties(prefix = "datasource.user.master.user1")
    public DataSource dsUser1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(USER_2_MASTER)
    @ConfigurationProperties(prefix = "datasource.user.master.user2")
    public DataSource dsUser2() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(USER_1_SLAVE)
    @ConfigurationProperties(prefix = "datasource.user.slave.user1")
    public DataSource dsUserSlave1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * user_2
     *
     * @return
     */
    @Bean(USER_2_SLAVE)
    @ConfigurationProperties(prefix = "datasource.user.slave.user2")
    public DataSource dsUserSlave2() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(USER_SHARDING_1)
    public DataSource masterSlave1(@Qualifier(USER_1_MASTER) DataSource dsUser1, @Qualifier(USER_1_SLAVE) DataSource dsUserSlave1) throws Exception {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put(USER_1_MASTER, dsUser1);
        dataSourceMap.put(USER_1_SLAVE, dsUserSlave1);
        MasterSlaveRuleConfiguration ruleConfiguration = new MasterSlaveRuleConfiguration("dsUser1", USER_1_MASTER, Lists.newArrayList(USER_1_SLAVE));
        return MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, ruleConfiguration, new ConcurrentHashMap<>());
    }

    @Bean(USER_SHARDING_2)
    public DataSource masterSlave2(@Qualifier(USER_2_MASTER) DataSource dsUser2, @Qualifier(USER_2_SLAVE) DataSource dsUserSlave2) throws Exception {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put(USER_2_MASTER, dsUser2);
        dataSourceMap.put(USER_2_SLAVE, dsUserSlave2);
        MasterSlaveRuleConfiguration ruleConfiguration = new MasterSlaveRuleConfiguration("dsUser2", USER_2_MASTER, Lists.newArrayList(USER_2_SLAVE));
        return MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, ruleConfiguration, new ConcurrentHashMap<>());
    }

    @Bean(USER_SHARDING_DATA_SOURCE)
    public DataSource dsUser(@Qualifier(USER_SHARDING_1) DataSource dsUser1, @Qualifier(USER_SHARDING_2) DataSource dsUser2) throws Exception {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("dsUser1", dsUser1);
        dataSourceMap.put("dsUser2", dsUser2);
        ShardingRuleConfiguration userRule = getUserRule();
        userRule.setDefaultDataSourceName("dsUser");
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, userRule, new ConcurrentHashMap<>(), new Properties());
    }

    /**
     * 配置分片规则
     *
     * @return
     */
    private ShardingRuleConfiguration getUserRule() {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new MemberIdShardingSchemeAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new MemberIdShardingTableAlgorithm()));
        shardingRuleConfig.getBindingTableGroups().add("t_user");
        return shardingRuleConfig;
    }

    @Bean("userShardingSqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier(USER_SHARDING_DATA_SOURCE) DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:usermapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("userTransaction")
    public DataSourceTransactionManager userTransactionManager(@Qualifier(USER_SHARDING_DATA_SOURCE) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
