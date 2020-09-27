package com.android.myapplicationtest.util.livedata.ex;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/*
 * LiveData 是一个包装器，可以与任何数据一起使用，包括实现集合的对象，例如 List。
 * LiveData 对象通常存储在 ViewModel 对象中，并通过 getter 方法访问
 */
public class NameViewModel<T> extends ViewModel {

    private MutableLiveData<T>  currentName;

    public MutableLiveData<T> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<T>();
        }
        return currentName;
    }
}
