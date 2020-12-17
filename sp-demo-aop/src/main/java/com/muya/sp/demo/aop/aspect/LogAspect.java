package com.muya.sp.demo.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("execution (public * com.muya.sp.demo.aop.controller..*.*(..))")
    public void logCut() {
    }

    /**
     * 统计方法执行耗时Around环绕通知
     *
     * @param joinPoint
     * @return
     */
    @Around("logCut()")
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();

        String returnCode = "Y";
        String guid = UUID.randomUUID().toString();

        try {
            log.info("Class: [{}], Method: [{}], Start: [{}]", joinPoint.getTarget().getClass().getName(),
                    joinPoint.getSignature().getName(), guid);
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            log.error("Class: [{}], Method: [{}] 执行耗时环绕通知出错, with exception: [{}]",
                    joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), e.getMessage());
            returnCode = "N";
            throw e;

        } finally {

            long endTime = System.currentTimeMillis();
            long diffTime = endTime - startTime;

            // 超过1秒的记录
            if (diffTime > 1000) {
                // 打印耗时的信息
                log.warn("Class: [{}], Method: [{}]: {}ms cost to long",
                        joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), diffTime);
            }
            log.info("Class: [{}], Method: [{}], ReturnCode: [{}], End: [{}]",
                    joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), returnCode, guid);
        }

        return obj;
    }
}
