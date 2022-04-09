package com.library.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description:
 */
@Target(ElementType.METHOD)
//表明这个注解可以写在方法之上，用来描述方法
@Retention(RetentionPolicy.RUNTIME)
//声明注解的有效时长:程序运行时才有效

/**
 * 注解里面不需要写东西，只起到标识的作用
 * 有这个标记，表示只有管理员才能访问
 */
public @interface LibrarianRequired {
}
