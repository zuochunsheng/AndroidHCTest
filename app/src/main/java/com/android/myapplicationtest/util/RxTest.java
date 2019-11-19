package com.android.myapplicationtest.util;

import android.content.Context;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author： zcs
 * @time：2019/10/25 on 16:08
 * @description：
 */
public class RxTest {


    public static void print(){
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);


        Observable<Number> numbers = Observable.just(1, 4.0, 3, 2.71, 2f, 7);
        Observable<Integer> integers = numbers.ofType(Integer.class);
        integers.subscribe((Integer x) -> System.out.print(x+" "));



    }
}
