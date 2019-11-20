package com.chunsheng.mylib.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author： zcs
 * @time：2019/11/20 on 17:16
 * @description：
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface BindView {
    int value();
}
