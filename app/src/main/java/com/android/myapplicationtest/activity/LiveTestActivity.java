package com.android.myapplicationtest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.android.myapplicationtest.R;
import com.android.myapplicationtest.util.livedata.NameViewModel;


public class LiveTestActivity extends AppCompatActivity {

    private TextView tvName;

    private NameViewModel nameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_test);
        tvName = findViewById(R.id.tv1);

        // Get the ViewModel.
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        // Create the observer which updates the UI.
        Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String newName) {
                // Update the UI, in this case, a TextView.
                tvName.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        nameViewModel.getCurrentName().observe(this, nameObserver);
//
//        String symbol ="symbol";
//        StockLiveData.get(symbol).observe(this, price -> {
//            // Update the UI.
//            tvName.setText(String.valueOf(price));
//        });
    }


    public void postValue(View view){
        //注意：必须调用 setValue(T) 方法才能从主线程更新 LiveData 对象。
        // 如果代码在工作线程中执行，则可以使用 postValue(T) 方法来更新 LiveData 对象。
        String anotherName = "John Doe";
        nameViewModel.getCurrentName().setValue(anotherName);

    }
}