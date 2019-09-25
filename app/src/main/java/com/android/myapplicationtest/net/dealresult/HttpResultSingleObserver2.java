package com.android.myapplicationtest.net.dealresult;

import com.android.myapplicationtest.net.HttpResultSingleObserver;


/**
 * @author： zcs
 * @time：2019/9/23 on 17:20
 * @description：DisposableSingleObserver 的子类，用来接收网络数据回调
 */
public abstract class HttpResultSingleObserver2<T> extends HttpResultSingleObserver<ResultBean<T>> {

    @Override
    public void onSuccess(ResultBean<T> t) {
        switch (t.getCode()) {
            case "success":
                //T data = t.getData();
                onSuccessResult(t.getData());
                //onSuccess(t.getData());
                break;
            default:
                //HttpResultException e = new HttpResultException(t.getCode(), t.getMessage());
                onFailure(t.getMessage());
                break;

        }
    }


//    @Override
//    public void onError(Throwable t) {
//       super.onError(t);
//    }




    /* ====================================*/


    /**
     * 请求成功回调
     *
     * @param result 回调数据
     */
    protected abstract void onSuccessResult(T result);

    /**
     * 请求失败回调
     *  @param error ，自定义异常
     * @param e       失败异常信息
     */
    //public abstract void onFailure(HttpError error, @NonNull Throwable e);


}
