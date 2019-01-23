package com.android.myapplicationtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.myapplicationtest.R;
import com.android.myapplicationtest.util.Config;
import com.google.gson.Gson;

import org.java_websocket.WebSocket;
import org.reactivestreams.Subscriber;

import java.util.HashMap;

import io.reactivex.CompletableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建client 实例
                createStompClient();

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

                //   var param = {
                //     'merchantid' : getStore('merchantid'),
                //     'tableid' : getStore('tableid'),
                //     'user' : userCode
                //   };
                //
                //   // store.state.stompClient.send('/ws/UserLogin', {}, JSON.stringify(param))

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



//                mStompClient.send("/app/welcome","{\"name\":\""+editText.getText()+"\"}")
//                        .subscribe(new Subscriber<Void>() {
//                            @Override
//                            public void onCompleted(){
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                e.printStackTrace();
//                                toast("发送错误");
//                            }
//
//                            @Override
//                            public void onNext(Void aVoid) {
//
//                            }
//                        });


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

}
