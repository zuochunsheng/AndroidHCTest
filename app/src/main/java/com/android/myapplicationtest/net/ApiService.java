package com.android.myapplicationtest.net;

import com.android.myapplicationtest.bean.ChildBean;
import com.android.myapplicationtest.net.dealresult.ResultBean;

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

    /**
     * 登录
     *
     * @param
     * @return 异步返回
     */
    @GET("app/recommend/pageList.json")
    //Single<ResponseBody> login(@Body LoginParament parament);//ok
    //Single<ParentBean> login();//ok
    Single<ResultBean<ChildBean>> login();//解剖code和message后的dataBean
}
