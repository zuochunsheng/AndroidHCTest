package com.android.myapplicationtest.util.executors;

import android.os.Looper;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CountDownLatchTest {

    //初始化CountDownLatch，设置需要等待的线程数量
    final CountDownLatch latch = new CountDownLatch(1);
    private volatile int sum;
    public int getSum(){
        //子线程
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Callable 子线程开始计算啦！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 5000; i++) {
                    sum = sum + i;
                }

                System.out.println("Callable子线程计算结束！");
                latch.countDown();
            }
        });
        //关闭线程池
        es.shutdown();

        try {
            //等待子线程执行完毕
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sum;
    }



    public static void main(String[] args) {
        int sum = new CountDownLatchTest().getSum();
        System.out.println("sum="+sum);
    }
}
