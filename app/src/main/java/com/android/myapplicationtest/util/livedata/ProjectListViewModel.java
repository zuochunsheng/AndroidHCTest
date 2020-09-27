package com.android.myapplicationtest.util.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.android.myapplicationtest.bean.mock.Project;
import com.android.myapplicationtest.logic.UserRepository;

import java.util.List;

public class ProjectListViewModel extends AndroidViewModel {


    private final LiveData<String> projectListObservable;

    public ProjectListViewModel(Application application) {
        super(application);


        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = UserRepository.getInstance().getProjectList("togallop");
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<String> getProjectListObservable() {
        return projectListObservable;
    }
}
