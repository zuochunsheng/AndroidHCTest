package com.android.myapplicationtest.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.myapplicationtest.KC_application;


/**
 * Created by dk_buke on 2017/12/27.
 */

public class ToastUtil {
    public static void makeTextShort(Context context,String info){
        Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
    }


    private static ToastUtil appToast;
    private Toast toastInstance;
    private long showTime;

    private ToastUtil() {

    }

    public static synchronized ToastUtil getInstance() {
        if (appToast == null) {
            appToast = new ToastUtil();
        }
        return appToast;
    }

    public void toast(String toastMsg) {
        if (TextUtils.isEmpty(toastMsg) || KC_application.getInstance() ==null) {
            return;
        }

        if (System.currentTimeMillis() - showTime >= 2000){
            toastInstance = Toast.makeText(KC_application.getInstance(), toastMsg, Toast.LENGTH_SHORT);
            toastInstance.show();
            showTime = System.currentTimeMillis();
        }

    }

}
