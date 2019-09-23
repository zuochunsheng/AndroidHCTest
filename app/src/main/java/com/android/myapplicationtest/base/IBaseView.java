package com.android.myapplicationtest.base;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:14
 * @description：
 */
public interface IBaseView {
    /**
     * @param msg 显示文本信息
     */
    void showMsg(String msg);

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 带文字信息显示loading
     */
    void showLoading(String msg);

    /**
     * 隐藏loading
     */
    void hideLoading();
}
