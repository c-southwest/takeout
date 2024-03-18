package com.sky.aspect;


import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.* (..)) && @annotation(com.sky.annotation.AutoFill)")
    void autoFillPointCut() {
    }

    ;


    @Before("autoFillPointCut()")
    void autoFill(JoinPoint joinPoint) {
        log.info("拦截mapper，填充公共字段");
        // 获取拦截方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AutoFill annotation = method.getAnnotation(AutoFill.class);
        // 获取参数对象
        Object[] args = joinPoint.getArgs();
        Object obj = args[0];
        // 获取赋值参数
        Long id = BaseContext.getCurrentId();
        LocalDateTime now = LocalDateTime.now();
        // 给参数对象赋值
        if (annotation != null) {
            try {
                Method setUpdateUser = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setUpdateTime = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                if (annotation.value() == OperationType.INSERT) {
                    Method setCreateTime = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                    Method setCreateUser = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                    setCreateTime.invoke(obj, now);
                    setCreateUser.invoke(obj, id);
                }
                setUpdateTime.invoke(obj, now);
                setUpdateUser.invoke(obj, id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
