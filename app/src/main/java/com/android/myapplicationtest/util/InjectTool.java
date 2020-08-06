package com.android.myapplicationtest.util;

import android.os.Handler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InjectTool {
    public static void dexInject() {

        try {
            // 通过反射调用 ActivityThread 的静态方法, 获取 currentActivityThread
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);

            // 获取 currentActivityThread 这个示例中的 mH
            Field handlerField = activityThreadClass.getDeclaredField("mH");
            handlerField.setAccessible(true);
            Handler handler = (Handler) handlerField.get(currentActivityThread);

            // 修改 mH 中的 callback 字段
            Field callbackField = Handler.class.getDeclaredField("mCallback");
            callbackField.setAccessible(true);
            Handler.Callback callback = (Handler.Callback) callbackField.get(handler);

            callbackField.set(handler, new LaunchCallback(callback));
        } catch (IllegalArgumentException | NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
