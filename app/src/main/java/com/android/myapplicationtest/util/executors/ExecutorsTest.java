package com.android.myapplicationtest.util.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ExecutorsTest {
    public static void main(String[] args) {

        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //提交任务并获取执行结果
        Future<Integer> future = es.submit(new Callable<Integer>() {
            private int sum;

            @Override
            public Integer call() throws Exception {
                System.out.println("Callable 子线程开始计算啦！");
                Thread.sleep(2000);

                for (int i = 0; i < 5000; i++) {
                    sum = sum + i;
                }
                System.out.println("Callable子线程计算结束！");
                return sum;

            }
        });
        //关闭线程池
        es.shutdown();

        try {
            Thread.sleep(1000);
            System.out.println("主线程在执行其他任务");

            if(future.get()!=null){
                //输出获取到的结果
                System.out.println("future.get()-->"+future.get());
            }else{
                //输出获取到的结果
                System.out.println("future.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }
}
