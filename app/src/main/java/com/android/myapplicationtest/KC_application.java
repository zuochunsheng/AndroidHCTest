package com.android.myapplicationtest;

import android.app.Application;
import android.content.Context;

import com.android.myapplicationtest.net.HttpManager;

import androidx.multidex.MultiDex;

/**
 * @author： zcs
 * @time：2019/7/9 on 14:25
 * @description：
 */
public class KC_application extends Application {

    private static KC_application instance;
    public static synchronized KC_application getInstance() {
        if (instance == null) {
            instance = new KC_application();
        }
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // HttpManager.setHTTP_URL(Config.APP_URL);

        HttpManager.getInstance()
                .setBaseUrl("https://api.github.com/")
                .setDebug(BuildConfig.DEBUG)
                .setOkHttpClient(HttpManager.getInstance().createDefaultClient())
                .setRetrofit(HttpManager.getInstance().createRetrofit());
    }
}
