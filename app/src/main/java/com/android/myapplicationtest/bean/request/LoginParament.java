package com.android.myapplicationtest.bean.request;

import com.android.myapplicationtest.bean.ParamentBean;


/**
 * @author： zcs
 * @time：2019/7/9 on 10:22
 * @description：
 */
public class LoginParament extends ParamentBean {
    private String username;
    private String password;
    private String mac;
    private String verfiycode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVerfiycode() {
        return verfiycode;
    }

    public void setVerfiycode(String verfiycode) {
        this.verfiycode = verfiycode;
    }
}
