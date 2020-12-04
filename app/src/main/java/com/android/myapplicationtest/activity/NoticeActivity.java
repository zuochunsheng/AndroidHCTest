package com.android.myapplicationtest.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.android.myapplicationtest.MainActivity;
import com.android.myapplicationtest.R;
import com.android.myapplicationtest.util.LogUtil;
import com.android.myapplicationtest.util.TimeFormatUtil;

import androidx.core.app.NotificationCompat;

//https://blog.csdn.net/u011418943/article/details/105133041/
public class NoticeActivity extends Activity {

    /**
     * 通知通道 ID，默认为 1，每次++
     */
    private int pushChannelId = 1;

    private NotificationManager notificationManager;
    private static final String CHANGEL_ID = "yonglong";
    // private static final String CHANGEL_NAME = "yonglong";

    private CountDownTimer timer;
    private TextView tvCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        TextView tvNotice = (TextView) findViewById(R.id.tv_notice);
        tvCountDown = (TextView) findViewById(R.id.tv_countDown);
        TextView tvCountDown2 = (TextView) findViewById(R.id.tv_countDown2);

        tvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotifictionIcon(NoticeActivity.this,"我是title，中国第一美男，我是title，中国第一美男","我是message，我是demo我不是测试我是text,我是message，我是demo我不是测试我是text");

//                if (timer != null) {
//                    timer.cancel();
//                    timer = null;
//                }
            }
        });

        tvCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdown(56, tvCountDown);
            }
        });
        tvCountDown2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdown(41, tvCountDown);
            }
        });

    }

    public void showNotifictionIcon(Context context, String title, String message) {

        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Intent broadcastIntent = new Intent(context, MainActivity.class);
        broadcastIntent.setAction(Intent.ACTION_VIEW);
        broadcastIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        broadcastIntent.putExtra("pushMessageServiceKey", "title");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder;
        pushChannelId++;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 8.0 系统中需要添加通知通道
            NotificationChannel mChannel = new NotificationChannel(String.valueOf(pushChannelId), getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
            builder = new NotificationCompat.Builder(context.getApplicationContext(), String.valueOf(pushChannelId));
        } else {
            builder = new NotificationCompat.Builder(context.getApplicationContext());
        }

        builder.setContentIntent(pendingIntent)
                .setFullScreenIntent(pendingIntent, true);

        @SuppressLint("WrongConstant")
        Notification serviceNotification = builder
                .setTicker("通知来啦！")
                .setContentTitle(title)           //通知栏的标题内容  单行
                .setContentText(message)          //通知的正文内容  单行

                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())       //通知创建的时间
                .setSmallIcon(R.drawable.push_small)                //通知显示的小图标，只能用alpha图层的图片进行设置
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.push))
                .setColor(getColor(R.color.gray))  //小图标 背景色
                .setPriority(NotificationCompat.PRIORITY_HIGH)      //用于设置通知的重要程度

                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setAutoCancel(true)                              //点击通知后，自动取消
                .setUsesChronometer(true)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .build();


        //serviceNotification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻，点击后通知栏不消失
        //channelId为本条通知的id
        notificationManager.notify(pushChannelId, serviceNotification);

    }

    private void countdown(int time, TextView tvCountDown) {
      LogUtil.e("time total :" + time);
//        LogUtil.e("timer == null :" + (timer == null));

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new CountDownTimer(time * 1000 + 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                LogUtil.e("onTick millisUntilFinished:"+ millisUntilFinished);
                //LogUtil.e("onTick int seconds:"+ (int)(millisUntilFinished *1.0 / 1000 -1));
                //String timeformat = TimeFormatUtil.formatDuration((int)(millisUntilFinished / 1000), 1);
                long round = Math.round((double) (millisUntilFinished *1.0/ 1000 -2));
                LogUtil.e("onTick round seconds:"+ round);
                String timeformat = TimeFormatUtil.formatDuration(round, 1);
                LogUtil.e("onTick timeformat:"+ timeformat);
                tvCountDown.setText(timeformat);
            }

            @Override
            public void onFinish() {
                LogUtil.e("onFinish ");
                tvCountDown.setText("倒计时完成");

            }

        };

        timer.start();

    }
}
