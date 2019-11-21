package com.android.myapplicationtest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author： zcs
 * @time：2019/11/21 on 15:04
 * @description：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface TestAnnotation {
    String value();
}
