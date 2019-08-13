package com.android.myapplicationtest.activity;

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
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

import com.android.myapplicationtest.R;

public class NoticeActivity extends Activity {

    /**
     * 通知通道 ID，默认为 1，每次++
     */
    private int pushChannelId = 1;

    private NotificationManager notificationManager;
    private static final String CHANGEL_ID = "yonglong";
    // private static final String CHANGEL_NAME = "yonglong";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        TextView tvNotice = (TextView) findViewById(R.id.tv_notice);

        tvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotifictionIcon(NoticeActivity.this);
            }
        });

    }

    public void showNotifictionIcon(Context context) {

        Intent broadcastIntent = new Intent(context, MainActivity.class);
        broadcastIntent.setAction(Intent.ACTION_VIEW);
        broadcastIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        broadcastIntent.putExtra("pushMessageServiceKey", "title");

        PendingIntent pendingIntent = PendingIntent.
                getActivity(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //适配8.0
            NotificationChannel channel = new NotificationChannel(CHANGEL_ID, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            notification = new Notification.Builder(context, CHANGEL_ID)
                    .setContentTitle("title")
                    .setContentText("text")
                    .setTicker("ticker")
                    .setContentIntent(pendingIntent)
                    .setColor(Color.parseColor("#32CD32"))
                    .setAutoCancel(true)//用户点击就自动消失
                    //.setUsesChronometer(true)
                    .setSmallIcon(R.drawable.ic_notifications_none_black_24dp)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.timg))
                    //.setOngoing(true)
                    .build();


        } else {
            notification = new NotificationCompat.Builder(context)
                    .setContentTitle("title")
                    .setContentText("text")
                    .setTicker("ticker")
                    .setContentIntent(pendingIntent)
                    .setChannelId(CHANGEL_ID)//适配8.0
                    .setColor(Color.parseColor("#32CD32"))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setAutoCancel(true)//用户点击就自动消失
                    //.setUsesChronometer(true)
                    .setSmallIcon(R.drawable.ic_notifications_none_black_24dp)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.timg))
                    //.setOngoing(true)
                    .build();

        }
        //android.R.drawable.ic_lock_idle_charging
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(pushChannelId++, notification);//每次改变mNotificationId的值才能在通知栏产生盖楼的效果
    }
}
