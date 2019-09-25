package com.android.myapplicationtest.logic;


import com.android.myapplicationtest.base.IBaseView;
import com.android.myapplicationtest.bean.request.LoginParament;


/**
 * anther: created by zcs on 2019/9/24 15 : 18
 * description :
 */
public interface LoginContract {
    interface IPresenter {
        void login();

    }

    interface IView extends IBaseView {

        LoginParament getLoginParament();

        void setLoginSuccessData(String s);
        void setLoginError(String error);

    }
}
