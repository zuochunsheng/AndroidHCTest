package com.chunsheng.mylib.processor;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author： zcs
 * @time：2019/11/19 on 16:51
 * @description：自定义注解
 */


@Target({ElementType.TYPE})   //---作用范围 Class
@Retention(RetentionPolicy.CLASS)  //---生命周期：仅保留到.class文件
public @interface Route {
    /** Path of route*/
    String path();  //---类似于成员变量
}
