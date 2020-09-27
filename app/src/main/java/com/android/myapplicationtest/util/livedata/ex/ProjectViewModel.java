package com.android.myapplicationtest.util.livedata.ex;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//封装 2
public class ProjectViewModel<T> extends ViewModel {

    private final String mKey;
    private MutableLiveData<T> mNameEvent = new MutableLiveData<>();

    public MutableLiveData<T> getNameEvent() {
        return mNameEvent;
    }

    public ProjectViewModel(String key) {
        mKey = key;
    }

    public static class Factory implements ViewModelProvider.Factory {
        private String mKey;

        public Factory(String key) {
            mKey = key;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ProjectViewModel(mKey);
        }
    }

    public String getKey() {
        return mKey;
    }
}
