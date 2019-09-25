package com.android.myapplicationtest.net;

import android.util.Log;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:16
 * @description：
 */
public class HttpManager {
    private Retrofit mRetrofit;
    private String mBaseUrl;
    private OkHttpClient mOkHttpClient;
    private Boolean debug = true;
    /* 请求超时时间 */
    private static final int TIME_OUT_PERIOD = 60 ;

    private static TrustManager[] trustManagers;
    static {
        trustManagers = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };
    }

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        return Holder.INSTANCE;
    }


    /**
     * @param mBaseUrl 设置BaseUrl
     *                 放在第一位设置
     */
    public HttpManager setBaseUrl(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
        return Holder.INSTANCE;
    }

    /**
     * 设置OkHttpClient
     */
    public HttpManager setOkHttpClient(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
        return Holder.INSTANCE;
    }

    /**
     * @param retrofit 设置retrofit
     *                 放在最后设置
     */
    public void setRetrofit(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    /**
     * debug
     */
    public HttpManager setDebug(Boolean debug) {
        this.debug = debug;
        return Holder.INSTANCE;
    }

    /**
     * @return mRetrofit.create(clazz)
     */
    public <T> T getApiService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    /**
     * 自带创建retrofit
     */
    public Retrofit createRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ObserveOnMainCallAdapterFactory.createMainScheduler())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
        return builder.build();
    }

    @NonNull
    private OkHttpClient.Builder getBuilder(boolean isHttpsSSL) {
        /* 对 okhttpClient 进行初始化 */
        OkHttpClient.Builder builder = new OkHttpClient.Builder().retryOnConnectionFailure(false);
        builder.connectTimeout(TIME_OUT_PERIOD, TimeUnit.SECONDS); // 设置连接超时时间 60 秒
        builder.readTimeout(TIME_OUT_PERIOD, TimeUnit.SECONDS); // 设置读取超时时间 60 秒
        builder.writeTimeout(TIME_OUT_PERIOD, TimeUnit.SECONDS); // 设置写入超时时间 60 秒

        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("zcs", "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        //OkHttp进行添加拦截器loggingInterceptor
        builder.addInterceptor(loggingInterceptor);
        // https 忽略证书验证
        if (isHttpsSSL) {
            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("SSL");

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            try {
                sslContext.init(null, trustManagers, new SecureRandom());
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(DO_NOT_VERIFY);
        }
        return builder;
    }

    /**
     * @return OkHttpclient
     */
    public OkHttpClient createDefaultClient() {
        OkHttpClient.Builder builder = getBuilder(true);
        return builder.build();
    }

    private static class Holder {
        private static final HttpManager INSTANCE = new HttpManager();
    }


}
