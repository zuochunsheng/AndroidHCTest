package com.zuo.testprocessor;


import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * @author： zcs
 * @time：2019/11/21 on 15:06
 * @description：
 */

// 注册
@AutoService(Processor.class)
//当前注解处理器 能够处理的注解 代替 getSupportedAnnotationTypes 方法
@SupportedAnnotationTypes({"com.android.myapplicationtest.annotation.TestAnnotation"})
//java 版本，代替getSupportedSourceVersion
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class TestProcessor extends AbstractProcessor {

    //日志输出类
    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (TypeElement typeElement : set) {
            messager.printMessage(Diagnostic.Kind.NOTE, "charSequence---------" + roundEnvironment.getElementsAnnotatedWith(typeElement));
            createJava();
        }

        return true;
    }



    private void createJava() {
        // 创建方法
        MethodSpec test = MethodSpec.methodBuilder("test")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addStatement("$T.out.println($S)", System.class, "hello")
                .addStatement("$T.out.println($S)", System.class, "wrold")
                .build();

        //创建类
        TypeSpec clazz = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(test)
                .build();


        JavaFile javaFile = JavaFile.builder("com.android.myapplicationtest.hello", clazz).build();
        //输出文件
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
//public class HelloWorld {
//    public static void test() {
//        System.out.println("hello");
//        System.out.println("wrold");
//    }
//}
