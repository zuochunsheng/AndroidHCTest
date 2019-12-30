package com.android.myapplicationtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.myapplicationtest.R;
import com.android.myapplicationtest.view.zhuanpan.LuckyPanView;

import java.util.Random;

public class CustomActivity extends Activity {

    private LuckyPanView mLuckyPanView;
    private ImageView mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        mLuckyPanView = (LuckyPanView) findViewById(R.id.id_luckypan);
        mStartBtn = (ImageView) findViewById(R.id.id_start_btn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLuckyPanView.isStart()) {
                    mStartBtn.setImageResource(R.drawable.ic_notifications_none_black_24dp);
                    mLuckyPanView.luckyStart(1);

                    //Random random=new Random();
                    //mLuckyPanView.luckyStart(random.nextInt()%6);

                } else {
                    if (!mLuckyPanView.isShouldEnd()) {
                        mStartBtn.setImageResource(R.mipmap.note);
                        mLuckyPanView.luckyEnd();
                    }
                }
            }
        });


    }
}
