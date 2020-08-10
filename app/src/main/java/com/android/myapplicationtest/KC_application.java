package com.android.myapplicationtest;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.android.myapplicationtest.net.HttpManager;
import com.android.myapplicationtest.util.InjectTool;
import com.android.myapplicationtest.util.TimberUtil;
import com.raizlabs.android.dbflow.config.FlowManager;

import timber.log.Timber;

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
        InjectTool.dexInject();
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


        //设置log自动在apk为debug版本时打开，在release版本时关闭
        TimberUtil.setLogAuto();
        //也可以设置log一直开
        //TimberUtil.setLogDebug();

        //打印tag为类名
        Timber.v("---onCreate---");


        //初始化DBFLOW
        FlowManager.init(this);

    }
}
