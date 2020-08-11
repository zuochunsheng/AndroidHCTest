package com.android.myapplicationtest.db.obox;

import com.android.myapplicationtest.KC_application;
import com.android.myapplicationtest.bean.UserEntity;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class DataManager {
    private static DataManager dataManager;

    public static synchronized DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public BoxStore boxStore;
    public Box<UserEntity> userEntityBox;

    public void init(KC_application demoApplication) {
        boxStore = demoApplication.getBoxStore();
        initUserEntityBox();
    }

    private void initUserEntityBox() {
        //对应操作对应表的类
        userEntityBox = boxStore.boxFor(UserEntity.class);
    }

}
