package com.android.myapplicationtest.util.livedata;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/*LiveDataManager.getInstance().with("goMainFragment", Integer.class)
        .observe(this, new Observer<Integer>() {
@Override
public void onChanged(Integer integer) {

        }
 });*/

/* LiveDataManager.getInstance().with("goMainFragment",Integer.class)
        .postValue(Integer.parseInt(loginToIndex));*/

public class LiveDataManager {
    private static LiveDataManager liveDataManager = new LiveDataManager();
    Map<String, MutableLiveData<Object>> map;

    private LiveDataManager() {
        map = new HashMap<>();
    }

    public static LiveDataManager getInstance() {
        return liveDataManager;
    }

    public<T> MutableLiveData<T> with(String key, Class<T> clazz){
        if(!map.containsKey(key)){
           map.put(key,new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) map.get(key);

    }
}
