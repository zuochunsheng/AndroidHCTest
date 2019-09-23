package com.android.myapplicationtest.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.ArraySet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:10
 * @description：
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public abstract class BaseMvpActivity  <P extends BasePresenter<? extends IBaseView>> extends BaseActivity implements IBaseView {

    private ProgressDialog mLoading;
    //主Presenter
    protected P mPresenter;
    //多个Presenter时候需要的容器
    private ArraySet<BasePresenter> mPresenters = new ArraySet<>(4);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = getPresenter();
        addToPresenters(mPresenter);

        initView(savedInstanceState);
        //initListener();
        //initData();
    }

    @Override
    protected void onDestroy() {
        for (BasePresenter presenter : mPresenters) {
            presenter.detachView();
        }
        mPresenters.clear();
        super.onDestroy();
    }

    @Override
    public void showLoading(String msg) {
        mLoading.setMessage(msg);
        if (!mLoading.isShowing()) {
            mLoading.show();
        }
    }

    @Override
    public void showLoading() {
        if (!mLoading.isShowing()) {
            mLoading.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

    @Override
    public void showMsg(String msg) {
        toastS(msg);
    }

    /**
     * 初始化Presenter，其他多个Presenter也在该方法创建并调用addToPresenters加入到集合
     * @return 主Presenter
     */
    protected abstract P getPresenter();

    /**
     * 根据具体项目需求创建loading
     */
    protected void initLoading() {
        mLoading = new ProgressDialog(this);
    }

    /**
     * 初始化View
     */
    protected void initView(@Nullable Bundle savedInstanceState){

    }

    /**
     * 初始化Listener
     */
    //protected abstract void initListener();

    /**
     * 初始化数据
     */
    //protected abstract void initData();

    /**
     * 把其他的Presenter添加到Presenters集合里
     * 这样会自动绑定View和管理内存释放
     */
    protected <T extends BasePresenter> void addToPresenters(T presenter) {
        presenter.attachView(this);
        mPresenters.add(presenter);
    }
}
