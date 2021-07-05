package com.muya.sp.demo.graceful.close.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020/5/7
 * @Time: 9:06
 * @Description: 异步/线程池执行定时任务
 */
@Configuration
@EnableScheduling
@EnableAsync
@Slf4j
public class SchedulerTaskConfig implements SchedulingConfigurer, AsyncConfigurer {

    /**
     * 定时任务使用的线程池
     *
     * @return
     */
    @Bean(destroyMethod = "shutdown", name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(50);
        scheduler.setThreadNamePrefix("grace-demo-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }

    /**
     * 异步任务执行线程池
     *
     * @return
     */
    @Bean(name = "asyncExecutor")
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(60);
        executor.setMaxPoolSize(100);
        executor.setThreadNamePrefix("nj-erp-taskExecutor-pool-%d");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(120000);
        executor.initialize();
        return executor;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = taskScheduler();
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("异步任务执行出现异常, message {}, method {}, params {}", throwable, method, objects);
        };
    }


}
