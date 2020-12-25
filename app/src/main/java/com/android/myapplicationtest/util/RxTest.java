package com.android.myapplicationtest.util;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author： zcs
 * @time：2019/10/25 on 16:08
 * @description：
 */
public class RxTest {


    private HandlerThread myHandlerThread;
    private Handler handler;

    public static void print() {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);


        Observable<Number> numbers = Observable.just(1, 4.0, 3, 2.71, 2f, 7);
        Observable<Integer> integers = numbers.ofType(Integer.class);
        integers.subscribe((Integer x) -> System.out.print(x + " "));


    }


    private void TdlTest() {
        //创建一个线程,线程名字：handler-thread
        myHandlerThread = new HandlerThread("handler-thread");
        //开启一个线程
        myHandlerThread.start();
        //在这个线程中创建一个handler对象
        handler = new Handler(myHandlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //这个方法是运行在 handler-thread 线程中的 ，可以执行耗时操作
                Log.d("handler ", "消息： " + msg.what + "  线程： " + Thread.currentThread().getName());
                return false;
            }
        });
        //在主线程给handler发送消息
        handler.sendEmptyMessage(1);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //在子线程给handler发送数据
//                handler.sendEmptyMessage( 2 ) ;
//            }
//        }).start() ;
    }
}
