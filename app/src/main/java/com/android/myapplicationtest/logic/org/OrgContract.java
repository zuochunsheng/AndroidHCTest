package com.android.myapplicationtest.logic.org;

import com.android.myapplicationtest.base.IBaseView;

/**
 * @author： zcs
 * @time：2019/9/23 on 18:03
 * @description：
 */
public interface OrgContract {
    interface View extends IBaseView {
        void showOrg(String org);
    }

    interface Presenter{
        void getOrg(String org);
    }
}
