package com.android.myapplicationtest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.android.myapplicationtest.R;
import com.android.myapplicationtest.bean.mock.Project;
import com.android.myapplicationtest.util.LogUtil;
import com.android.myapplicationtest.util.livedata.LiveDataManager;
import com.android.myapplicationtest.util.livedata.ex.NameViewModel;
import com.android.myapplicationtest.view.zhuanpan.LuckyPanView;


public class CustomActivity extends AppCompatActivity {

    private LuckyPanView mLuckyPanView;
    private ImageView mStartBtn;

    int time = 0;

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

//        Project project = new Project();
//        project.setBoid("boid-cus");
//        project.setTitle("wosititle-cus");
//        project.setType("2-cus");
//
//        LiveDataManager.getInstance()
//                .with("goJavaBean",Project.class)
//                .postValue(project);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    time++;
                    LogUtil.e("thread" + time);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


    }

}
