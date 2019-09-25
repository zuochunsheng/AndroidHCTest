package com.android.myapplicationtest.net.dealresult;


import androidx.annotation.Keep;

/**
 * @author： zcs
 * @time：2019/1/11 on 15:15
 * @description：
 */
@Keep
public abstract class AbstractResultBean  {

    public abstract boolean success();


    public abstract String getCode();


    public abstract String getMessage() ;

}
