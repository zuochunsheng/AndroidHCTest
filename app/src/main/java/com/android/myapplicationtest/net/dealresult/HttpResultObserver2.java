package com.android.myapplicationtest.net.dealresult;


import com.android.myapplicationtest.net.HttpResultObserver;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:20
 * @description：DisposableObserver 的子类，用来接收网络数据回调
 */
public abstract class HttpResultObserver2<T> extends HttpResultObserver<ResultBean<T>> {


    @Override
    public void onNext(ResultBean<T> t) {
        switch (t.getCode()) {
            case "success":
                //T data = t.getData();
                onSuccessResult(t.getData());
                break;
            default:
                onFailure(t.getMessage());
                break;

        }
    }


//    @Override
//    public void onComplete() {
//        super.onComplete();
//    }
//
//
//
//    @Override
//    public void onError(Throwable t) {
//       super.onError(t);
//    }




    /* ====================================*/

    protected abstract void onSuccessResult(T result);


}
