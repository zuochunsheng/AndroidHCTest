package com.android.myapplicationtest.util;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.android.myapplicationtest.KC_application;

public class LaunchCallback  implements Handler.Callback  {
    public static final int LAUNCH_ACTIVITY = 100;

    private Handler.Callback originCallback;
    public LaunchCallback(Handler.Callback originCallback) {
        this.originCallback = originCallback;
    }


    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == LAUNCH_ACTIVITY) {
//            Toast.makeText(
//                    KC_application.getInstance(),
//                    "activity is going to launch! ", Toast.LENGTH_SHORT).show();
            LogUtil.e("activity is going to launch! ");
        }
        //return originCallback.handleMessage(msg);
        return false;
    }
}
