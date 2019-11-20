package com.chunsheng.mylib.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * @author： zcs
 * @time：2019/11/19 on 17:00
 * @description：https://www.cnblogs.com/limingblogs/p/8074582.html
 */

@AutoService(Processor.class)
public class HelloProcessor extends AbstractProcessor {
    /** 文件相关的辅助类 用于生成新的源文件、class等 */
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        // 主要做一些初始化操作
        mFiler = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //具体处理注解的逻辑，控制代码的生成

        // 构建方法 此处使用到了square公司的javapoet库，用来辅助生成 类的代码
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("show")
                .addModifiers();
        methodBuilder.addStatement("String test = \"$N\" ","hello annotation world!");

        /** 构建类 */
        TypeSpec finderClass = TypeSpec.classBuilder("Hello$$Inject")
                .addModifiers()//Modifier.PUBLIC
                .addMethod(methodBuilder.build())
                .build();
        try {
            JavaFile.builder("com.android.myapplicationtest",finderClass).build().writeTo((File) mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // 支持的注解类型
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // 支持处理的注解类型, 在这里就是@Hello
        Set<String> types = new LinkedHashSet<>();
        types.add(Hello.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        //java版本 如：jdk1.6or jdk1.7
        //return super.getSupportedSourceVersion();
        return SourceVersion.latestSupported();
    }
}
