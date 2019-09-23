package com.android.myapplicationtest.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.android.myapplicationtest.R;
import com.android.myapplicationtest.base.BaseMvpActivity;
import com.android.myapplicationtest.logic.org.OrgContract;
import com.android.myapplicationtest.logic.org.OrgPresenter;
import com.android.myapplicationtest.logic.user.UserContract;
import com.android.myapplicationtest.logic.user.UserPresenter;
import com.android.myapplicationtest.util.LogUtil;

import androidx.annotation.Nullable;

public class MvpRequestActivity extends BaseMvpActivity<UserPresenter> implements UserContract.View, OrgContract.View  {


    private TextView tvMsg;
    private TextView tvMsg2;
    private Button btnClick;
    private Button btnClick2;

    private OrgPresenter mOrgPresenter;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        tvMsg = findViewById(R.id.tv_msg);
        tvMsg2 = findViewById(R.id.tv_msg2);
        btnClick = findViewById(R.id.btn_click);
        btnClick2 = findViewById(R.id.btn_click2);

        initListener();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_request;
    }

    @Override
    protected UserPresenter getPresenter() {
        mOrgPresenter = new OrgPresenter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addToPresenters(mOrgPresenter);
        }
        return new UserPresenter();
    }


    protected void initListener() {
        btnClick.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPresenter.getUser("togallop");
                }
            }
        });
        btnClick2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mOrgPresenter.getOrg("google");
            }
        });

    }



    @Override
    public void showOrg(String org) {
        tvMsg2.setText(org);
    }

    @Override
    public void showUser(String msg) {
        tvMsg.setText(msg);
    }




}
