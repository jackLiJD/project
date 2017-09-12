package com.example.lijinduo.mydemo.mvp.mvpinterface;
/**
 * Created by lijinduo on 2017/4/5.
 */

public interface LoginModel {
    void login(String username,String password,OnLoginFinishedListener listener);
}
