package com.android.myapplicationtest;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.myapplicationtest.util.Config;
import com.android.myapplicationtest.util.LogUtil;
import com.google.gson.Gson;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import io.reactivex.CompletableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.leolin.shortcutbadger.ShortcutBadger;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.LifecycleEvent;
import ua.naiksoftware.stomp.dto.StompMessage;



public class MainActivity extends AppCompatActivity {

    private TextView serverMessage;
    private Button start;
    private Button stop;
    private Button send;
    private EditText editText;
    private Button subcribe;


    private StompClient mStompClient;

    private void bindView() {
        serverMessage = (TextView) findViewById(R.id.serverMessage);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        send = (Button) findViewById(R.id.send);
        editText = (EditText) findViewById(R.id.clientMessage);
        subcribe = (Button) findViewById(R.id.subcribe);

    }


    private static final String TAG = "zuo";
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        bindView();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setBadgenumber(MainActivity.this,6);
                //setBadgeNumber(7);

                setBadgeNum(4);
                //setNotification();


                //创建client 实例
                //createStompClient();
            }
        });
        subcribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //订阅消息
                registerStompTopic();
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("merchantid", "007JP1458120001");
                map.put("tableid", "88");
                map.put("user", "adfasdfsfsfsdfsfs");

                Gson gson = new Gson();
                String json = gson.toJson(map);
                mStompClient.send("/ws/UserLogin", json)
                        .compose(applySchedulers())
                        .subscribe(() -> {
                            Log.d(TAG, "REST echo send successfully");
                        }, throwable -> {
                            Log.e(TAG, "Error send REST echo", throwable);
                            toast(throwable.getMessage());
                        });


            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStompClient.disconnect();
            }
        });

    }

    //创建client 实例
    private void createStompClient() {

        //mStompClient = Stomp.over(WebSocket.class, Config.WS_URI);
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, Config.WS_URI);
        mStompClient.connect();

        mStompClient.lifecycle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LifecycleEvent>() {
                    @Override
                    public void accept(LifecycleEvent lifecycleEvent) throws Exception {
                        switch (lifecycleEvent.getType()) {
                            case OPENED:
                                Log.e(TAG, "Stomp connection opened");
                                toast("连接已开启");
                                break;

                            case ERROR:
                                Log.e(TAG, "Stomp Error", lifecycleEvent.getException());
                                toast("连接出错");
                                break;
                            case CLOSED:
                                Log.e(TAG, "Stomp connection closed");
                                toast("连接关闭");
                                break;
                        }
                    }
                });


    }

    //点对点订阅，根据用户名来推送消息
    private void registerStompTopic() {
        //订阅/table/"+tableid+"/info这个主题
        String tableid = "88";
        mStompClient.topic("/table/" + tableid + "/info")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StompMessage>() {
                    @Override
                    public void accept(StompMessage stompMessage) throws Exception {
                        // {"orderDetails":{"Client9260133611":{"34":{"49":{"price":"10","num":1,"name":"name of 34 49","id":49}}}},"processer":"adfasdfsfsfsdfsfs","notifyText":"","preorderId":"100"}
                        Log.e(TAG, "forlan debug msg is " + stompMessage.getPayload());
                    }
                });

//        id:13ce09f7-db94-4083-af86-e7098d1f46df
//        destination:/table/88/info
//        ack:auto


    }


    private void toast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected CompletableTransformer applySchedulers() {
        return upstream -> upstream
                .unsubscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    //设置角标-华为 -ok
    private void setBadgenumber(Context context, int i) {
        Bundle extra = new Bundle();
        extra.putString("package", "com.android.myapplicationtest");//应用包名

        extra.putString("class", "com.android.myapplicationtest.MainActivity");//桌面图标对应的应用入口Activity类
        extra.putInt("badgenumber", i);
        context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, extra);

    }

    //设置角标-vivo/oppo/小米  不支持
    private void setBadgeNumber(int i) {
        Notification.Builder builder = new Notification.Builder(this).setContentTitle("title").setContentText("text").setSmallIcon(R.drawable.ic_notifications_none_black_24dp);

        Notification notification = builder.build();
        //获取手机品牌
        String phoneName = android.os.Build.MODEL;
        LogUtil.e("bd","phoneName:"+phoneName);//V1s-G
        //vivo 角标设置 -2019-10-28 现在不支持设置角标
        //if (phoneName.equals("vivo Y51")) {
//            Intent intent2 = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
//            intent2.putExtra("packageName", "com.android.myapplicationtest");
//            intent2.putExtra("className", "com.android.myapplicationtest.MainActivity");
//            //显示的角标数！
//            intent2.putExtra("notificationNum", i);
//            sendBroadcast(intent2);

//        Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
//        LogUtil.e("bd","getPackageName:"+getPackageName());//com.android.myapplicationtest
//        intent.putExtra("packageName", getPackageName());
//        String launchClassName = getPackageManager().getLaunchIntentForPackage(getPackageName()).getComponent().getClassName();
//        LogUtil.e("bd","launchClassName:"+launchClassName);//com.android.myapplicationtest.MainActivity
//        intent.putExtra("className", launchClassName);
//        intent.putExtra("notificationNum", i);
//        sendBroadcast(intent);
        //}

        // oppo 角标设置 -2019-10-28
        Intent intent2 = new Intent("com.oppo.unsettledevent");
        intent2.putExtra("pakeageName", getPackageName());
        intent2.putExtra("number", i);
        intent2.putExtra("upgradeNumber", i);
//        if (canResolveBroadcast(getApplicationContext(), intent2)) {
//            sendBroadcast(intent2);
//        } else {
            try {
                Bundle extras = new Bundle();
                extras.putInt("app_badge_count", i);
                getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, extras);
            } catch (Throwable t) {
                t.printStackTrace();
            }
       // }

        //小米角标 角标设置 -2019-10-28
//        else {
//            try {
//                Field field = notification.getClass().getDeclaredField("extraNotification");
//                Object extraNotification = field.get(notification);
//                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
//                method.invoke(extraNotification, i);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            mNotificationManager.notify(0, notification);
//        }

    }


    // 通知
    private int pushChannelId = 0;
    private void setNotification() {
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent serviceNotificationIntent = new Intent();

        //将通知携带的数据一步步传递到MainActivity
        serviceNotificationIntent.setClass(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, serviceNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 当 app 处于后台，弹出通知提示
        NotificationCompat.Builder builder;

        notificationManager.cancelAll();
        pushChannelId++;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 8.0 系统中需要添加通知通道
            NotificationChannel mChannel = new NotificationChannel(String.valueOf(pushChannelId), "123", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
            builder = new NotificationCompat.Builder(getApplicationContext(), String.valueOf(pushChannelId));
        } else {
            builder = new NotificationCompat.Builder(getApplicationContext());
        }

        //if (headingTxnId != null) {
        builder.setContentIntent(pendingIntent)
                .setFullScreenIntent(pendingIntent, true);
        //}
        @SuppressLint("WrongConstant")
        Notification serviceNotification = builder
                .setTicker("通知来啦！")
                .setContentTitle("您有一条新交易消息！")             //指定通知栏的标题内容
                //.setContentText("this is content text")          //通知的正文内容
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())               //通知创建的时间
                .setSmallIcon(R.mipmap.icon_white)                //通知显示的小图标，只能用alpha图层的图片进行设置
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setColor(Color.parseColor("#b1130a"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)      //用于设置通知的重要程度

                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setAutoCancel(true)                              //点击通知后，自动取消
                .setUsesChronometer(true)
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setBadgeIconType(R.mipmap.note)// 没有作用
                .setNumber(10)// 没有作用
                .build();

        //serviceNotification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻，点击后通知栏不消失
        //channelId为本条通知的id
        notificationManager.notify(pushChannelId, serviceNotification);
    }


   /* ===============三方库=================*/
    private void setBadgeNum(int badgeCount){
        //华为 ok
        ShortcutBadger.applyCount(this, badgeCount); //for 1.1.4+

    }



}
