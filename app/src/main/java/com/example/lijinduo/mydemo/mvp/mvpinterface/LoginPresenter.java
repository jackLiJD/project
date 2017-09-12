package com.example.lijinduo.mydemo.mvp.mvpinterface;

/**
 * Created by lijinduo on 2017/4/5.
 */

public interface LoginPresenter {

    void validateCredentials(String username,String password);

    void onDestory();
}
