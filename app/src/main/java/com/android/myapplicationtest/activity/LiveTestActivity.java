package com.android.myapplicationtest.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.myapplicationtest.R;
import com.android.myapplicationtest.util.LogUtil;
import com.android.myapplicationtest.util.livedata.LiveDataManager;
import com.android.myapplicationtest.util.livedata.ex.NameViewModel;

import com.android.myapplicationtest.bean.mock.Project;
import com.android.myapplicationtest.util.livedata.ex.NetworkLiveData;
import com.android.myapplicationtest.util.livedata.ex.ProjectViewModel;

/*
 * https://blog.csdn.net/weixin_30556161/article/details/98778103
 *
 */
public class LiveTestActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvName2;
    private TextView tvName3;

    private NameViewModel nameViewModel;
    private ProjectViewModel projectViewModel;
    private ProjectViewModel projectViewMode2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_test);
        tvName = findViewById(R.id.tv1);
        tvName2 = findViewById(R.id.tv2);
        tvName3 = findViewById(R.id.tv3);

        // Get the ViewModel.
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        // Create the observer which updates the UI.
        // base type
        Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String newName) {
                // Update the UI, in this case, a TextView.
                LogUtil.e("change", "newName=" + newName);
                tvName.setText(newName);
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        nameViewModel.getCurrentName().observe(this, nameObserver);


        // -------------

        //1 javaBean
        projectViewMode2 = ViewModelProviders.of(this, new ProjectViewModel.Factory("projectTest2")).get(ProjectViewModel.class);
        projectViewMode2.getNameEvent().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(Project newName) {
                LogUtil.e("change", newName);
                tvName2.setText(newName.getTitle());
            }
        });


        //2 自定义
        NetworkLiveData.getInstance(this).observe(this, new Observer<NetworkInfo>() {
            @Override
            public void onChanged(@Nullable NetworkInfo networkInfo) {
                Log.e("onChanged", "networkInfo=" + networkInfo);
            }
        });


//        LiveDataManager.getInstance().with("goMainFragment", Integer.class)
//                .observe(this, new Observer<Integer>() {
//                    @Override
//                    public void onChanged(Integer integer) {
//                        LogUtil.e("change", integer);
//                        tvName3.setText(String.valueOf(integer));
//                    }
//                });

        LiveDataManager.getInstance().with("goJavaBean", Project.class)
                .observe(this, new Observer<Project>() {
                    @Override
                    public void onChanged(Project javaBean) {
                        LogUtil.e("change", javaBean);
                        tvName3.setText(javaBean.getTitle());
                    }
                });

    }


    public void postValue(View view) {
        //注意：必须调用 setValue(T) 方法才能从主线程更新 LiveData 对象。
        // 如果代码在工作线程中执行，则可以使用 postValue(T) 方法来更新 LiveData 对象。
        String anotherName = "普通用法";
        nameViewModel.getCurrentName().setValue(anotherName);

//        调用 setValue 方法，Observer 的 onChanged 方法会在调用 serValue 方法的线程回调。
//        而 postvalue 方法，Observer 的 onChanged 方法将会在主线程回调。

    }

    public void postValue2(View view) {

//        String anotherName = "封装2 ";
//        projectViewModel.getNameEvent().postValue(anotherName);

        Project project = new Project();
        project.setBoid("boid");
        project.setTitle("wosititle");
        project.setType("2");
        projectViewMode2.getNameEvent().postValue(project);

    }

    public void postValue3(View view) {

//        LiveDataManager.getInstance()
//                .with("goMainFragment",Integer.class)
//                .postValue(2);

        Project project = new Project();
        project.setBoid("boid-manager");
        project.setTitle("wosititle-manager");
        project.setType("2-manager");

        LiveDataManager.getInstance()
                .with("goJavaBean",Project.class)
                .postValue(project);

    }
}