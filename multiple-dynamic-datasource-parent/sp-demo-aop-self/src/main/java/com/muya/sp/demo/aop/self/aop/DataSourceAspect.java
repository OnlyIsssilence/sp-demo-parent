package com.muya.sp.demo.aop.self.aop;

import com.muya.sp.demo.aop.self.annotation.DataSource;
import com.muya.sp.demo.aop.self.holder.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 22:40
 * @Description:
 */
@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {

    /*
    方法一不用注解,强耦合业务
    @Pointcut("execution(* com.muya.sp.demo.aop.self.mapper.db1..*.*(..))")
    private void db1Aspect() {
    }

    @Pointcut("execution(* com.muya.sp.demo.aop.self.mapper.db2..*.*(..))")
    private void db2Aspect() {
    }

    @Before("db1Aspect()")
    public void db1() {
        log.info("切换到db1 数据源...");
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceEnum.DB1.getValue());
    }

    @Before("db2Aspect()")
    public void db2() {
        log.info("切换到db2 数据源...");
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceEnum.DB2.getValue());
    }*/

    /*方案2:使用注解,只有在method上设置生效 方案3改造
    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DataSource ds) throws Throwable {
        log.info("选择数据源---" + ds.value().getValue());
        DynamicDataSourceContextHolder.setDataSourceKey(ds.value().getValue());
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, DataSource ds) {
        log.info("Revert DataSource : {} > {}", ds.value().getValue(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }*/

    @Around("execution(@com.muya.sp.demo.aop.self.annotation.DataSource * *(..))")
    public Object aroundController(ProceedingJoinPoint pjp) throws Throwable {
        // 获取切入的 Method
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();

        boolean flag = method.isAnnotationPresent(DataSource.class);
        if (flag) {
            DataSource dataSource = method.getAnnotation(DataSource.class);
            setDataSource(dataSource, method);
        } else {
            // 如果方法上没有注解，则搜索类上是否有注解
            DataSource classDataSource = AnnotationUtils.findAnnotation(joinPointObject.getMethod().getDeclaringClass(), DataSource.class);
            if (classDataSource != null) {
                setDataSource(classDataSource, method);
            } else {
                DynamicDataSourceContextHolder.clearDataSourceKey();
                log.info("没有注解使用默认的");
            }
        }
        Object proceed = pjp.proceed();
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        return proceed;
    }

    private void setDataSource(DataSource dataSource, Method method) {
        if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value().getValue())) {
            log.info("DataSource [{}] doesn't exist, use default DataSource [{}] " + dataSource.value());
        } else {
            // 切换数据源
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value().getValue());
            log.info("Switch DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey() +
                    "] in Method [" + method.getName() + "]");
        }
    }
}
