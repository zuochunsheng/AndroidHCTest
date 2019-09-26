package com.android.myapplicationtest.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.ArraySet;

import io.reactivex.annotations.Nullable;

/**
 * anther: created by zcs on 2019/2/27 18 : 39
 * description :
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements IBaseView {

    private ProgressDialog mLoading;
    protected P mPresenter;
    //多个Presenter时候需要的容器
    @SuppressLint("NewApi")
    private ArraySet<BasePresenter> mPresenters = new ArraySet<>(4);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (isSetPresenter()) {
            mPresenter = createPresenter();
            addToPresenters(mPresenter);
        }

    }

    protected boolean isSetPresenter() {
        return false;
    }

    protected abstract P createPresenter();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLoading();
    }

//    @Override
//    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
//        super.onLazyInitView(savedInstanceState);
//        initLoading();
//    }

    @SuppressLint("NewApi")
    @Override
    public void onDetach() {
        super.onDetach();
        if (isSetPresenter()) {
            for (BasePresenter presenter : mPresenters) {
                presenter.detachView();
            }
            mPresenters.clear();
            // HttpRequestPool.getInstance().cancelRequest(getNetWorkTag());
        }
    }

    /**
     * 根据具体项目需求创建loading
     */
    /**
     * 根据具体项目需求创建loading
     */
    protected void initLoading() {
        if (mLoading == null) {
            mLoading = new ProgressDialog(this.getActivity());
        }
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
    public Context getContext() {
        //return this.getContext();//循环调用
        return this.getActivity();
    }


    /**
     * 把其他的Presenter添加到Presenters集合里
     * 这样会自动绑定View和管理内存释放
     */
    protected <T extends BasePresenter> void addToPresenters(T presenter) {
        presenter.attachView(this);
        mPresenters.add(presenter);
    }


}
