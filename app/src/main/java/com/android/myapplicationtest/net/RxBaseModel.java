package com.android.myapplicationtest.net;

import com.android.myapplicationtest.bean.ChildBean;
import com.android.myapplicationtest.bean.request.LoginParament;
import com.android.myapplicationtest.net.dealresult.HttpResultSingleObserver2;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:50
 * @description：
 */
public class RxBaseModel extends BaseModel {

      //all
//    public Disposable login(LoginParament parament, HttpResultSingleObserver<ParentBean> observer) {
//        return getApiService().login()
//                .subscribeWith(observer);
//    }

     //解剖code和message后的data数据
    public Disposable login(LoginParament parament, HttpResultSingleObserver2<ChildBean> observer) {
        return getApiService().login()
                .subscribeWith(observer);

    }

      //all
//    public Disposable sms(SmsParament parament, HttpResultObserver<ParentBean> observer) {
//        return getApiService().sms()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(observer);
//
//    }

    //解剖code和message后的data数据
//    public Disposable sms(SmsParament parament, HttpResultObserver2<ChildBean> observer) {
//        return getApiService().sms()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(observer);
//
//    }

    /*=================== 以上为四中返回Observer类型 =====================*/


}
