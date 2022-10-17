package com.homyit.aspect;

import com.alibaba.fastjson.JSON;
import com.homyit.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.homyit.annotation.SystemLog)")
    public void pt() {

    }

    @Around("pt()")
    public Object pointLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            //结束后换行
            log.info("=============End=============" + System.lineSeparator());
        }

        return ret;
    }

//    @AfterThrowing(pointcut = "pt()", throwing = "ex")
//    public void handler(Throwable ex) {
//        log.info("Response          : {}", JSON.toJSONString(ResultVo.error(((BizException) ex.getCause()).getError())));
//    }

    private void handleAfter(Object ret) {
        //打印出参
        log.info("Response          : {}", JSON.toJSONString(ret));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取被增强方法上的增强的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=============Start=============");
        //打印请求URL
        log.info("URL               : {}", request.getRequestURL());
        //打印描述信息
        log.info("BusinessName      : {}", systemLog.businessName());
        //打印Http Method
        log.info("Http Method       : {}", request.getMethod());
        //打印调用controller到全路径以及执行方法
        log.info("Class Method      : {}.{}", joinPoint.getSignature().getDeclaringType(),
                ((MethodSignature) joinPoint.getSignature()).getName());
        //打印请求的IP
        log.info("IP                : {}", request.getRemoteHost());
        //打印请求入参
        log.info("Request Args      : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(SystemLog.class);
    }
}
