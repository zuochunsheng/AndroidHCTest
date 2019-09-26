package com.android.myapplicationtest.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

/**
 * authr : edz on 2017/8/28  上午11:45
 * describe :测试 打印日志，手动
 * 区别正式 测试 环境
 */

public class LogUtil {

    private static final boolean isTest = true;//Config.isTest;// IsTest.IS_TEST;
    private final static String TAG ="tag";

    public static  void e(String msg){
        if(isTest){
            if(!TextUtils.isEmpty(msg)){
                Log.e(TAG,msg);
            }
        }
    }
    public static  void e(String tag, String msg){
        if(isTest){
            if(!TextUtils.isEmpty(msg)){
                Log.e(tag,msg);
            }
        }
    }

    public static void e(Object object){
        if(isTest){
            Gson gson = new Gson();
            String s = gson.toJson(object);
            if(!TextUtils.isEmpty(s)){
                Log.e(TAG,s);
            }

        }

    }
    public static void e(String tag, Object object){
        if(isTest){
            Gson gson = new Gson();
            String msg = gson.toJson(object);
            if(!TextUtils.isEmpty(msg)){
                Log.e(tag,msg);
            }
        }
    }
    public static void e(String tag, String msgTitle, Object object){
        if(isTest){
            Gson gson = new Gson();
            String s = gson.toJson(object);
            if(!TextUtils.isEmpty(s)){
                Log.e(tag,msgTitle +":"+s);
            }

        }

    }


    public static void e(String msg, Throwable tr) {
        if(isTest){
            if(!TextUtils.isEmpty(msg) && tr != null){
                Log.e(TAG,msg,tr);
            }
        }
    }
    public static void e(String tag, String msg, Throwable tr) {
        if(isTest){
            if(!TextUtils.isEmpty(msg) && tr != null){
                Log.e(tag,msg,tr);
            }
        }
    }


}
