package com.example.lijinduo.mydemo.mvp.mvpmodel;

import android.os.Handler;
import android.text.TextUtils;

import com.example.lijinduo.mydemo.mvp.mvpinterface.LoginModel;
import com.example.lijinduo.mydemo.mvp.mvpinterface.OnLoginFinishedListener;

/**
 * Created by lijinduo on 2017/4/5.
 */

public class LoginModelImpl implements LoginModel{
    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameError();

                }else if (TextUtils.isEmpty(password)) {
                    listener.onPasswordError();
                }else {
                    listener.onSuccess();
                }

            }
        });
    }
}
