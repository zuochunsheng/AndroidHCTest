package com.chunsheng.mylib.processor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  https://blog.csdn.net/fengxingzhe001/article/details/78520298
 * @author： zcs
 * @time：2019/11/19 on 16:42
 * @description： 动物名称注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnimalName {
    String value() default "动物";
}
