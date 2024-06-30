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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * AOP切面，实现字段自动填充
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill) ")
    public void autoFillPointcut() {

    }

    /**
     * 前置通知
     */
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);

        Object [] args = joinPoint.getArgs();
        if (args == null || args.length == 0)return;

        Object o = args[0];//方法接受到的参数，比如employee
        if (autoFill.value() == OperationType.INSERT){
            try {
                //通过方法名和形参类型确定方法
                Method setCreateTimeMethod = o.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUserMethod = o.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER,Long.class);
                Method setUpdateTimeMethod = o.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
                Method setUpdateUserMethod = o.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);

                //通过反射赋值
                setCreateTimeMethod.invoke(o,LocalDateTime.now());
                setCreateUserMethod.invoke(o,BaseContext.getCurrentId());
                setUpdateTimeMethod.invoke(o,LocalDateTime.now());
                setUpdateUserMethod.invoke(o,BaseContext.getCurrentId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (autoFill.value() == OperationType.UPDATE) {
            try {
                Method setUpdateTimeMethod = o.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUserMethod = o.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);

                setUpdateTimeMethod.invoke(o,LocalDateTime.now());
                setUpdateUserMethod.invoke(o,BaseContext.getCurrentId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
