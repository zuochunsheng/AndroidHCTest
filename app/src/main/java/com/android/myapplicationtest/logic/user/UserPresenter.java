package com.android.myapplicationtest.logic.user;

import android.text.TextUtils;
import com.android.myapplicationtest.base.BasePresenter;
import io.reactivex.disposables.Disposable;
import com.android.myapplicationtest.net.HttpResultObserver;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:56
 * @description：
 */
public class UserPresenter extends BasePresenter<UserContract.View> implements UserContract.Presenter {

    private UserModel mModel;

    public UserPresenter() {
        mModel = getModel(UserModel.class);
    }

    @Override
    public void getUser(String userName) {

        if (TextUtils.isEmpty(userName)) {
            mView.showMsg("用户名不能为空");
            return;
        }

        mView.showLoading("正在加载...");
        Disposable disposable = mModel.getUser(userName, new HttpResultObserver<String>() {
            @Override
            protected void onResult(String s) {
                mView.showUser(s);
                mView.hideLoading();
            }

            @Override
            protected void onFailure(Throwable e) {
                mView.showUser(e.getMessage());
                mView.hideLoading();
            }
        });
        addDisposable(disposable);
    }
}
