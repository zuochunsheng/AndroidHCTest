package com.android.myapplicationtest.net;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author： zcs
 * @time：2019/9/23 on 17:26
 * @description：
 */
public interface ApiService {
    @GET("users/{username}")
    Single<String> getUser(@Path("username") String userName);

    @GET("orgs/{org}")
    Single<String> getOrg(@Path("org") String org);
}
