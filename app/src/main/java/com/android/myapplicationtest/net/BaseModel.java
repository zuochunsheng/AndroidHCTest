package com.android.myapplicationtest.net;

import com.android.myapplicationtest.base.IBaseModel;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:50
 * @description：
 */
public abstract class BaseModel implements IBaseModel {
    private ApiService apiService;

    protected ApiService getApiService() {
        if (apiService == null) {
            apiService = HttpManager.getInstance().getApiService(ApiService.class);
        }
        return apiService;
    }

}
