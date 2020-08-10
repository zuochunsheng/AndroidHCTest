package com.android.myapplicationtest.bean;

import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class HashMapClass extends HashMap {

    public static void main(String[] args) {
        // 所有方法
//        Method[] methods = HashMapClass.class.getMethods();
//
//        for (Method method : methods) {
//            System.out.println("method name is " + method.getName());
//        }


        //testObject("java.lang.Object");
        testStudent();


    }

    private static void testStudent() {
        //反射构建 Student 对象
//        try {
//            Class studentClass = Student.class;
//
//            // 参数类型为一个 String 的构造函数
//            Constructor constructor = studentClass.getConstructor(new Class[]{String.class});
//
//            // 实例化 student 对象
//            Student student = (Student)constructor.newInstance("Li Lei");
//            System.out.print(student.getName());
//
//        } catch (ReflectiveOperationException e) {
//            e.printStackTrace();
//        }

        //反射修改私有变量
//        try {
//            Student student = new Student("Han MeiMei");
//            //System.out.println("origin grade is " + student.getGrade());//1
//
//            Class studentClass = Student.class;
//            // 获取声明的 grade 字段，这里要注意 getField 和 getDeclaredField 的区别
//            Field gradeField = studentClass.getDeclaredField("grade");
//
//            // 字段修饰符如果是 private 或者 package 权限的，一定要赋予其访问权限
//            gradeField.setAccessible(true);
//
//            // 修改 student 对象中的 Grade 字段值
//            gradeField.set(student, 2);
//            System.out.println("after reflection grade is " + student.getGrade());
//
//        } catch (ReflectiveOperationException e) {
//            e.printStackTrace();
//        }

        //反射调用私有方法
        try {
            Student student = new Student("Han MeiMei");

            // 获取私有方法，同样注意 getMethod 和 getDeclaredMethod 的区别
            Method goMethod = Student.class.getDeclaredMethod("goToSchool");
            // 方法修饰符如果是private 或者 package 权限的 ,赋予访问权限
            goMethod.setAccessible(true);

            // 调用 goToSchool 方法。
            goMethod.invoke(student);

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

    }

    private static void testObject(String className){

        try {
            Class mClass = Class.forName(className);

            // 不包含包名前缀的名字
            String simpleName = mClass.getSimpleName();//Object
            String name = mClass.getName();//java.lang.Object

            // 类型修饰符, private, protect, static etc.
            int modifiers = mClass.getModifiers();//1
            // Modifier 提供的一些用于判读类型的静态方法.
            Modifier.isPrivate(modifiers);//false
            Modifier.isPublic(modifiers);

            // 父类的信息
            Class superclass = mClass.getSuperclass();
            // 构造函数
            Constructor[] constructors = mClass.getConstructors();

            // 字段类型
            Field[] fields = mClass.getFields();
            for (int i = 0; i <fields.length ; i++) {
                System.out.println("field =" + fields[i].getName());
            }
//            for (Field field : fields) {
//                System.out.println("field =" + field.getName());
//
//            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getSystemProperty(String property, String defaultValue) {
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Method getter = clazz.getDeclaredMethod("get", String.class);//方法名，参数类型,一个参数
            String value = (String) getter.invoke(clazz.newInstance(), property);
            if (!TextUtils.isEmpty(value)) {
                return value;
            }
        } catch (Exception e) {
            Log.e("tag", "Unable to read system properties");
        }
        return defaultValue;
    }


}
