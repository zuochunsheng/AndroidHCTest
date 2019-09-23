package com.android.myapplicationtest.net;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:20
 * @description：
 */
public abstract  class HttpResultObserver <T> extends DisposableSingleObserver<T> {
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
        onFailure(e);
    }

    /**
     * @param t 获取结果
     */
    protected abstract void onResult(T t);

    /**
     * @param e 获取结果失败
     */
    protected abstract void onFailure(Throwable e);

}
