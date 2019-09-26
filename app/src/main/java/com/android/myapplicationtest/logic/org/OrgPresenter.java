package com.android.myapplicationtest.logic.org;

import com.android.myapplicationtest.base.BasePresenter;
import com.android.myapplicationtest.net.HttpResultSingleObserver;
import com.android.myapplicationtest.util.LogUtil;

import io.reactivex.disposables.Disposable;

/**
 * @author： zcs
 * @time：2019/9/23 on 18:05
 * @description：
 */
public class OrgPresenter extends BasePresenter<OrgContract.View> implements OrgContract.Presenter {

    private OrgModel mModel;

    private Disposable mDisposable;

    public OrgPresenter() {
        mModel = getModel(OrgModel.class);
    }

    @Override
    public void getOrg(String org) {
        //判断是否正在执行getOrg请求，防止重复请求
        if (isNotDisposed(mDisposable)) {
            LogUtil.e(mDisposable.isDisposed());
            return;
        }
        mView.showLoading();
        mDisposable = mModel.getOrg(org, new HttpResultSingleObserver<String>() {
            @Override
            protected void onResult(String s) {
                mView.showOrg(s);
                mView.hideLoading();
            }

            @Override
            protected void onFailure(String error) {
                mView.showMsg(error);
                mView.hideLoading();
            }

        });
        addDisposable(mDisposable);
    }
}
