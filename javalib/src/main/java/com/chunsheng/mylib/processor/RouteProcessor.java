package com.chunsheng.mylib.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * @author： zcs
 * @time：2019/11/19 on 16:55
 * @description：
 */
public class RouteProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        // 主要做一些初始化操作
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //具体处理注解的逻辑，控制代码的生成
        return processAnnotations();
    }

    private boolean processAnnotations() {
        return false;
    }

    //    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        // 支持处理的注解类型, 在这里就是@Route
//
//    }
//
    @Override
    public SourceVersion getSupportedSourceVersion() {
        //java版本 如：jdk1.6or jdk1.7
        return super.getSupportedSourceVersion();
    }
}
