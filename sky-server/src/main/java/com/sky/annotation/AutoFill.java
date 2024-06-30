package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，标识方法进行自动填充
 */
@Target(ElementType.METHOD)//对什么起效
@Retention(RetentionPolicy.RUNTIME)//什么时候生效
public @interface AutoFill {
    OperationType value();
}
