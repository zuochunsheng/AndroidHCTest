package com.android.myapplicationtest.logic.imp;

import com.android.myapplicationtest.base.BasePresenter;
import com.android.myapplicationtest.bean.ChildBean;
import com.android.myapplicationtest.logic.LoginContract;
import com.android.myapplicationtest.net.RxBaseModel;
import com.android.myapplicationtest.net.dealresult.HttpResultSingleObserver2;
import com.android.myapplicationtest.net.dealresult.ResultBean;
import com.android.myapplicationtest.util.LogUtil;

import io.reactivex.disposables.Disposable;

/**
 * @author： zcs
 * @time：2019/7/10 on 15:32
 * @description：
 */
public class LoginPresenter extends BasePresenter<LoginContract.IView> implements LoginContract.IPresenter {

    private RxBaseModel mModel;

    public LoginPresenter() {
        mModel = getModel(RxBaseModel.class);
    }


    @Override
    public void login() {
        mView.showLoading();

//        Disposable disposable = mModel.login(mView.getLoginParament(), new HttpResultSingleObserver<ResponseBody>() {
//            @Override
//            protected void onResult(ResponseBody responseBody) {
//                String string = "";
//                try {
//                    string = responseBody.string();
//                    mView.setLoginSuccessData(string);
//                    mView.hideLoading();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            protected void onFailure(Throwable e) {
//                LogUtil.e("login onFailure", e.getMessage());
//                mView.setLoginError(e.getMessage());
//                mView.hideLoading();
//            }
//        });

//        Disposable disposable = mModel.login(mView.getLoginParament(), new HttpResultSingleObserver<ParentBean>() {
//            @Override
//            protected void onResult(ParentBean parentBean) {
//                LogUtil.e("login onResult", parentBean);
//                mView.setLoginSuccessData("");
//                mView.hideLoading();
//            }
//
//            @Override
//            protected void onFailure(Throwable e) {
//                LogUtil.e("login onFailure", e.getMessage());
//                mView.setLoginError(e.getMessage());
//                mView.hideLoading();
//            }
//        });

        Disposable disposable = mModel.login(mView.getLoginParament(), new HttpResultSingleObserver2<ChildBean>() {

            @Override
            protected void onSuccessResult(ChildBean result) {
                LogUtil.e("login onSuccessResult", result);
                mView.setLoginSuccessData("");
                mView.hideLoading();
            }


            @Override
            protected void onResult(ResultBean<ChildBean> childBeanResultBean) {
                LogUtil.e("login onResult", childBeanResultBean);
            }

            @Override
            protected void onFailure(String error) {
                LogUtil.e("login onFailure", error);
                mView.setLoginError(error);
                mView.hideLoading();
            }
        });

        addDisposable(disposable);
    }
}
