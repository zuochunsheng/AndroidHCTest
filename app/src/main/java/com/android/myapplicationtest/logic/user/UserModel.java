package com.android.myapplicationtest.logic.user;

import com.android.myapplicationtest.net.BaseModel;
import com.android.myapplicationtest.net.HttpResultObserver;

import io.reactivex.disposables.Disposable;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:57
 * @description：
 */
public class UserModel extends BaseModel {
    public Disposable getUser(String userName, HttpResultObserver<String> observer) {
        return getApiService().getUser(userName).subscribeWith(observer);
    }
}
