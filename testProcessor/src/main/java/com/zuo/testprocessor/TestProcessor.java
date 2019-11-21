package com.zuo.testprocessor;


import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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


//    TypeSpec是类/接口/枚举的抽象，
//    MethodSpec是方法/构造函数的抽象，
//    FieldSpec是成员变量/字段的抽象。
    private void createJava() {

        //1 创建 成员变量
        FieldSpec fieldSpec =  FieldSpec.builder(String.class ,"name",Modifier.PRIVATE).build();

        //2.1 创建方法
        // addModifiers 对方法的修饰约束，
        // addParameter 添加方法参数 ，
        // addStatement 方法体，
        // returns 返回值
        MethodSpec method = MethodSpec.methodBuilder("test")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addStatement("$T.out.println($S)", System.class, "hello")
                .addStatement("$T.out.println($S)", System.class, "wrold")
                .build();

        //或者 2.2
        MethodSpec method2 = MethodSpec.methodBuilder("test2")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addCode(""
                        +  "System.out.println("+"\"hello\""+");\n"
                        +  "System.out.println("+"\"world\""+");\n"
                        )
                .build();


        //2.3 创建 构造方法
        MethodSpec constructor =   MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class,"name")
                .addStatement("this.$N = $N", "name", "name")
                .build();

        //创建 匿名类
        TypeSpec comparator = TypeSpec.anonymousClassBuilder("")
                .addSuperinterface(ParameterizedTypeName.get(Comparator.class, String.class))
                .addMethod(MethodSpec.methodBuilder("compare")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(String.class, "a")
                        .addParameter(String.class, "b")
                        .returns(int.class)
                        .addStatement("return $N.length() - $N.length()", "a", "b")
                        .build())
                .build();


        //3 创建类
        TypeSpec clazz = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                .addField(fieldSpec)

                .addMethod(constructor)
                .addMethod(method)
                .addMethod(method2)
                .addMethod(MethodSpec.methodBuilder("sortByLength")
                        .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                        .addParameter(ParameterizedTypeName.get(List.class, String.class), "strings")
                        .addStatement("$T.sort($N, $L)", Collections.class, "strings", comparator)
                        .build())
                .build();


        //4 Java文件
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
//    private String name;
//    public HelloWorld(String name){
//      this.name = name;
//    }
//
//    public static void test() {
//        System.out.println("hello");
//        System.out.println("wrold");
//    }
//}
