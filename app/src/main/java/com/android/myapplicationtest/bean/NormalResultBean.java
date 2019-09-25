package com.android.myapplicationtest.bean;



import androidx.annotation.Keep;

/**
 * @author： zcs
 * @time：2019/1/11 on 15:15
 * @description：
 */
@Keep
public class NormalResultBean {

    /**
     * code : 000000
     * data : null
     * message : SUCCESS
     */

    private String code;

    private String message;



    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }



    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
