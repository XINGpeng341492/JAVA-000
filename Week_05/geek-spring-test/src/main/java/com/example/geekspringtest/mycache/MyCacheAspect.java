package com.example.geekspringtest.mycache;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/17 8:24 下午
 **/
@Component
@Aspect
@Slf4j
public class MyCacheAspect {

    @Around("execution(* com.example.geekspringtest.test.*.*(..))")
    public void doAround(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Class<?>[] argTypes = new Class[joinPoint.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(),argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        MyCache myCache = method.getAnnotation(MyCache.class);
        if (null != myCache) {
            log.info("open cache:{},cacheTime:{}", myCache.open(), myCache.seconds());
            if(myCache.open()) {
                log.info("添加缓存处理。。。todo ");
            }

        }


    }

    private void doBefore() {
        log.info("doBefore...todo ");

    }

    private void doAfter() {
        log.info("doAfter...todo ");
    }
}
