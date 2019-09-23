package com.android.myapplicationtest.logic.user;

import com.android.myapplicationtest.base.IBaseView;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:58
 * @description：
 */
public interface UserContract {
    interface View extends IBaseView {
        void showUser(String msg);
    }

    interface Presenter {
        void getUser(String userName);
    }

}
