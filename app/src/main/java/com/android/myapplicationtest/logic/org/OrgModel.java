package com.android.myapplicationtest.logic.org;

import com.android.myapplicationtest.net.BaseModel;
import com.android.myapplicationtest.net.HttpResultObserver;

import io.reactivex.disposables.Disposable;

/**
 * @author： zcs
 * @time：2019/9/23 on 18:02
 * @description：
 */
public class OrgModel  extends BaseModel {
    public Disposable getOrg(String org, HttpResultObserver<String> observer) {
        return getApiService().getOrg(org).subscribeWith(observer);
    }
}
