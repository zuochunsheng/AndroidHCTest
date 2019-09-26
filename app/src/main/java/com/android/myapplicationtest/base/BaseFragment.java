package com.android.myapplicationtest.base;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.myapplicationtest.util.ToastUtil;

import androidx.annotation.LayoutRes;
import io.reactivex.annotations.Nullable;


/**
 * anther: created by zcs on 2019/9/24 18 : 39
 * description :view
 */
public abstract class BaseFragment extends Fragment implements View.OnTouchListener {


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container,false);
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }


    protected void toast(String msg){
        ToastUtil.getInstance().toast(msg);
    }


    /*********************子类实现*****************************/

    //获取布局文件
    @LayoutRes
    protected abstract int getLayoutId();

}
