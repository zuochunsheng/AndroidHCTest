package com.android.myapplicationtest.logic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.myapplicationtest.bean.mock.Project;
import com.android.myapplicationtest.logic.user.UserModel;
import com.android.myapplicationtest.net.HttpResultSingleObserver;
import com.android.myapplicationtest.util.livedata.LiveDataManager;


public class UserRepository {

    private final UserModel userModel;
    public UserRepository(){
        userModel = new UserModel();
    }

    private static UserRepository userRepository = new UserRepository();
    public static UserRepository getInstance(){
        return userRepository;
    }


    public LiveData<String> getProjectList(String userName) {
        //final MutableLiveData<List<Project>> data = new MutableLiveData<>();
        final MutableLiveData<String> data = new MutableLiveData<>();

        userModel.getUser(userName, new HttpResultSingleObserver<String>() {
            @Override
            protected void onResult(String s) {
                data.setValue(s);
            }

            // Error handling will be explained in the next article …
            @Override
            protected void onFailure(String error) {
               // data.setValue(error);
            }
        });

        return data;
    }

}
