package com.example.lijinduo.mydemo.mvp.mvpinterface;

/**
 * Created by lijinduo on 2017/4/5.
 */

//用于回传到actvity
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void setnavigateToHome();

    void setOtherthing();



}
