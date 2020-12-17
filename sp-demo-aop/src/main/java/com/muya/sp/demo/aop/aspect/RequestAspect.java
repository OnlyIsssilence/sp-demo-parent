package com.muya.sp.demo.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class RequestAspect {

    @Pointcut("execution (public * com.muya.sp.demo.aop.controller..*.*(..))")
    public void webReq() {
    }

    /**
     * 记录请求信息
     *
     * @param joinPoint
     * @return
     */
    @Around("webReq()")
    public Object reqAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = joinPoint.proceed();
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setIp(request.getRemoteAddr());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()));
        requestInfo.setRequestParams(getRequestParamsByProceedingJoinPoint(joinPoint));
        requestInfo.setResult(result);
        requestInfo.setTimeCost(System.currentTimeMillis() - start);

        ObjectMapper trans = new ObjectMapper();

        log.info("WebLog Request Info      : {}", trans.writeValueAsString(requestInfo));

        return result;

    }

    /**
     * 异常通知环绕体
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webReq()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
        requestErrorInfo.setIp(request.getRemoteAddr());
        requestErrorInfo.setUrl(request.getRequestURL().toString());
        requestErrorInfo.setHttpMethod(request.getMethod());
        requestErrorInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()));
        requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        requestErrorInfo.setException(e);

        ObjectMapper trans = new ObjectMapper();

        try {
            log.info("WebLog Error Request Info      : {}", trans.writeValueAsString(requestErrorInfo));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取入参
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }
            if (value instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest)value;
                value = JSON.toJSONString(request.getParameterMap());
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }

    @Data
    public class RequestInfo {
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private Object result;
        private Long timeCost;
    }

    @Data
    public class RequestErrorInfo {
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private RuntimeException exception;
    }
}
