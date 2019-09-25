package com.android.myapplicationtest.net;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:20
 * @description：DisposableSingleObserver 的子类，用来接收网络数据回调
 */
public abstract class HttpResultSingleObserver<T> extends DisposableSingleObserver<T> {

    @Override
    public void onSuccess(T t) {
        //dispose 一次任务
        dispose();
        onResult(t);
    }

    @Override
    public void onError(Throwable e) {
        //dispose 一次任务
        dispose();
        onFailure(e.getMessage());
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
