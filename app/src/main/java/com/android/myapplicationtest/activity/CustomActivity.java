package com.android.myapplicationtest.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.android.myapplicationtest.R;
import com.android.myapplicationtest.bean.mock.Project;
import com.android.myapplicationtest.util.LogUtil;
import com.android.myapplicationtest.util.livedata.LiveDataManager;
import com.android.myapplicationtest.util.livedata.ex.NameViewModel;
import com.android.myapplicationtest.view.zhuanpan.LuckyPanView;
import com.chunsheng.permission.IPermission;
import com.chunsheng.permission.PermissionUtil;

import java.io.IOException;
import java.util.List;


public class CustomActivity extends AppCompatActivity {

    private LuckyPanView mLuckyPanView;
    private ImageView mStartBtn;

    int time = 0;


    String latLongString;
    private LocationManager locationManager;

    private double latitude = 0;
    private double longitude = 0;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            double[] data = (double[]) msg.obj;

            LogUtil.e("location","经度：" + data[0] + "\t纬度:" + data[1]);
            List<Address> addList = null;
            Geocoder ge = new Geocoder(getApplicationContext());
            try {
                addList = ge.getFromLocation(data[0], data[1], 1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (addList != null && addList.size() > 0) {
                for (int i = 0; i < addList.size(); i++) {
                    Address ad = addList.get(i);
                    latLongString = ad.getLocality();
                }
            }
            LogUtil.e("location",latLongString);
        }

    };


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

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    time++;
//                    LogUtil.e("thread" + time);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }).start();


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        PermissionUtil.getInstance(this).requestRunTimePermission(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        }, new IPermission() {
            @Override
            public void onGranted() {
                getJW();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {

            }
        });

    }

    /**
     * 确定按钮监听
     *
     * @param
     */
    public void getJW() {
        new Thread() {
            @Override
            public void run() {
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude(); // 经度
                    longitude = location.getLongitude(); // 纬度
                    double[] data = { latitude, longitude };
                    Message msg = handler.obtainMessage();
                    msg.obj = data;
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }


}
