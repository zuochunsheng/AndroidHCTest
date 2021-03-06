package com.android.myapplicationtest.net;

import io.reactivex.observers.DisposableObserver;


/**
 * @author： zcs
 * @time：2019/9/23 on 17:20
 * @description：DisposableSingleObserver 的子类，用来接收网络数据回调
 */
public abstract class HttpResultObserver<T> extends DisposableObserver<T> {


    @Override
    public void onNext(T t) {
        onResult(t);
    }

    @Override
    public void onComplete() {
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        //dispose 一次任务
        onFailure(e.getMessage());
        dispose();

    }

    /**
     * @param t 获取结果
     */
    protected abstract void onResult(T t);

    /**
     * @param error 获取结果失败
     */
    protected abstract void onFailure(String error);

}
